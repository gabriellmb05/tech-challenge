package br.com.on.fiap.adaptadores.saida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.adaptadores.saida.servico.PersistenciaProdutoAdaptador;
import br.com.on.fiap.hexagono.dominio.Produto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersistenciaProdutoAdaptadorTest {

	@Mock
	private ProdutoRepositorio produtoRepositorio;

	@Mock
	private ProdutoSaidaMapeador produtoSaidaMapeador;

	@InjectMocks
	private PersistenciaProdutoAdaptador persistenciaProdutoAdaptador;

	@Test
	@DisplayName("Dado um produto existente, quando buscar por ID, então ele deve ser retornado")
	void dadoProdutoExistente_quandoBuscarPorId_entaoDeveSerRetornado() {
		Long id = 1L;
		ProdutoEntidade produtoEntidade = new ProdutoEntidade();
		Produto produto = new Produto();
		when(produtoRepositorio.findById(id)).thenReturn(Optional.of(produtoEntidade));
		when(produtoSaidaMapeador.paraProduto(produtoEntidade)).thenReturn(produto);

		Optional<Produto> resultado = persistenciaProdutoAdaptador.buscaProdutoPorId(id);

		assertTrue(resultado.isPresent());
		assertEquals(produto, resultado.get());
		verify(produtoRepositorio).findById(id);
		verify(produtoSaidaMapeador).paraProduto(produtoEntidade);
	}

	@Test
	@DisplayName("Dado um produto novo, quando salvar, então ele deve ser persistido")
	void dadoProdutoNovo_quandoSalvar_entaoDeveSerPersistido() {
		Produto produto = new Produto();
		ProdutoEntidade produtoEntidade = new ProdutoEntidade();
		ProdutoEntidade produtoPersistidoEntidade = new ProdutoEntidade();
		Produto produtoPersistido = new Produto();
		when(produtoSaidaMapeador.paraEntidade(produto)).thenReturn(produtoEntidade);
		when(produtoRepositorio.save(produtoEntidade)).thenReturn(produtoPersistidoEntidade);
		when(produtoSaidaMapeador.paraProduto(produtoPersistidoEntidade)).thenReturn(produtoPersistido);

		Produto resultado = persistenciaProdutoAdaptador.salvaProduto(produto);

		assertEquals(produtoPersistido, resultado);
		verify(produtoSaidaMapeador).paraEntidade(produto);
		verify(produtoRepositorio).save(produtoEntidade);
		verify(produtoSaidaMapeador).paraProduto(produtoPersistidoEntidade);
	}

	@Test
	@DisplayName("Dado um produto existente, quando buscar por nome, então ele deve ser retornado")
	void dadoProdutoExistente_quandoBuscarPorNome_entaoDeveSerRetornado() {
		String nome = "Produto Teste";
		ProdutoEntidade produtoEntidade = new ProdutoEntidade();
		Produto produto = new Produto();
		when(produtoRepositorio.findByNome(nome)).thenReturn(Optional.of(produtoEntidade));
		when(produtoSaidaMapeador.paraProduto(produtoEntidade)).thenReturn(produto);

		Optional<Produto> resultado = persistenciaProdutoAdaptador.buscaProdutoPorNome(nome);

		assertTrue(resultado.isPresent());
		assertEquals(produto, resultado.get());
		verify(produtoRepositorio).findByNome(nome);
		verify(produtoSaidaMapeador).paraProduto(produtoEntidade);
	}

	@Test
	@DisplayName("Dado um produto existente, quando deletar por ID, então ele deve ser removido")
	void dadoProdutoExistente_quandoDeletarPorId_entaoDeveSerRemovido() {
		Long id = 1L;

		persistenciaProdutoAdaptador.deletaProdutoPorId(id);

		verify(produtoRepositorio).deleteById(id);
	}
}
