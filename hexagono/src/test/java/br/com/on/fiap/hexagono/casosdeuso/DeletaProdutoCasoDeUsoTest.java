package br.com.on.fiap.hexagono.casosdeuso;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.casosdeuso.DeletaProdutoCasoDeUso;
import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.portas.saida.PersisteProdutoPortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletaProdutoCasoDeUsoTest {

  @Mock private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

  @InjectMocks private DeletaProdutoCasoDeUso deletaProdutoCasoDeUso;

  @Test
  @DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
  void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido() {
    Long id = 1L;
    when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.of(new Produto()));

    deletaProdutoCasoDeUso.deleta(id);

    verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
    verify(persisteProdutoPortaSaida).deletaProdutoPorId(id);
  }

  @Test
  @DisplayName(
      "Dado um produto não existente, quando deletar o produto, então deve lançar uma exceção")
  void dadoProdutoNaoExistente_quandoDeletarProduto_entaoDeveLancarExcecao() {
    Long id = 1L;
    when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.empty());

    assertThrows(ProdutoNaoEncontratoExcecao.class, () -> deletaProdutoCasoDeUso.deleta(id));
    verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
    verify(persisteProdutoPortaSaida, never()).deletaProdutoPorId(id);
  }
}
