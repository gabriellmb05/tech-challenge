package br.com.on.fiap.hexagono.casosdeuso;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.casosdeuso.produto.AlteraProdutoCasoDeUso;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlteraProdutoCasoDeUsoTest {

	@Mock
	private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

	@InjectMocks
	private AlteraProdutoCasoDeUso alteraProdutoCasoDeUso;

	@Test
	@DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
	void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado() {
		Long id = 1L;
		Produto produto = new Produto();
		produto.setNome("Produto Teste");

		when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.of(new Produto()));
		when(persisteProdutoPortaSaida.salvaProduto(produto)).thenReturn(produto);

		Produto resultado = alteraProdutoCasoDeUso.alterar(id, produto);

		assertAll(() -> assertEquals(id, resultado.getId()), () -> assertEquals("Produto Teste", resultado.getNome()));
		verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
		verify(persisteProdutoPortaSaida).salvaProduto(produto);
	}

	@Test
	@DisplayName("Dado um produto não existente, quando alterar o produto, então deve lançar uma exceção")
	void dadoProdutoNaoExistente_quandoAlterarProduto_entaoDeveLancarExcecao() {
		Long id = 1L;
		Produto produto = new Produto();

		when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.empty());

		assertThrows(ProdutoNaoEncontratoExcecao.class, () -> alteraProdutoCasoDeUso.alterar(id, produto));

		verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
		verify(persisteProdutoPortaSaida, never()).salvaProduto(produto);
	}
}
