package br.com.on.fiap.hexagono.casosdeuso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.casosdeuso.produto.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InsereProdutoCasoDeUsoTest {

  @Mock private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

  @InjectMocks private InsereProdutoCasoDeUso insereProdutoCasoDeUso;

  @Test
  @DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
  void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo() {
    Produto produto = new Produto();
    produto.setNome("Produto Teste");
    when(persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome()))
        .thenReturn(Optional.empty());
    when(persisteProdutoPortaSaida.salvaProduto(produto)).thenReturn(produto);

    Produto resultado = insereProdutoCasoDeUso.inserir(produto);

    assertEquals(produto.getNome(), resultado.getNome());
    verify(persisteProdutoPortaSaida).buscaProdutoPorNome(produto.getNome());
    verify(persisteProdutoPortaSaida).salvaProduto(produto);
  }

  @Test
  @DisplayName("Dado um produto existente, quando inserir o produto, então deve lançar uma exceção")
  void dadoProdutoExistente_quandoInserirProduto_entaoDeveLancarExcecao() {
    Produto produto = new Produto();
    produto.setNome("Produto Teste");
    when(persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome()))
        .thenReturn(Optional.of(produto));

    assertThrows(ProdutoExistenteExcecao.class, () -> insereProdutoCasoDeUso.inserir(produto));
    verify(persisteProdutoPortaSaida).buscaProdutoPorNome(produto.getNome());
    verify(persisteProdutoPortaSaida, never()).salvaProduto(produto);
  }
}
