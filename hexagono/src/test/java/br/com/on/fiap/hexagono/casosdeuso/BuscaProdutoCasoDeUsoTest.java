package br.com.on.fiap.hexagono.casosdeuso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaProdutoCasoDeUsoTest {

  @Mock private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

  @InjectMocks private BuscaProdutoCasoDeUso buscaProdutoCasoDeUso;

  @Test
  @DisplayName("Dado um produto existente, quando buscar o produto, então ele deve ser retornado")
  void dadoProdutoExistente_quandoBuscarProduto_entaoDeveSerRetornado() {
    Long id = 1L;
    Produto produto = new Produto();
    produto.setId(id);
    when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));

    Produto resultado = buscaProdutoCasoDeUso.buscar(id);

    assertEquals(id, resultado.getId());
    verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
  }

  @Test
  @DisplayName(
      "Dado um produto não existente, quando buscar o produto, então deve lançar uma exceção")
  void dadoProdutoNaoExistente_quandoBuscarProduto_entaoDeveLancarExcecao() {
    Long id = 1L;
    when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.empty());

    assertThrows(ProdutoNaoEncontratoExcecao.class, () -> buscaProdutoCasoDeUso.buscar(id));
    verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
  }
}
