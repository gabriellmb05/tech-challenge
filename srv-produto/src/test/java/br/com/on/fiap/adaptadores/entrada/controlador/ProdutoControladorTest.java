package br.com.on.fiap.adaptadores.entrada.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.produto.AlteraProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.produto.DeletaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.produto.InsereProdutoPortaEntrada;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProdutoControladorTest {

	@Mock
	private BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;

	@Mock
	private InsereProdutoPortaEntrada insereProdutoPortaEntrada;

	@Mock
	private AlteraProdutoPortaEntrada alteraProdutoPortaEntrada;

	@Mock
	private DeletaProdutoPortaEntrada deletaProdutoPortaEntrada;

	@Mock
	private ProdutoEntradaMapeador produtoEntradaMapeador;

	@InjectMocks
	private ProdutoControlador produtoControlador;

	static Stream<Arguments> produtoDTOProvider() {
		return Stream.of(
				Arguments.of(new ProdutoRespostaDTO(1L, "Produto 1", "Descricao 1", BigDecimal.valueOf(10.0)),
						new ProdutoSolicitacaoDTO("Produto 1", "Descricao 1", BigDecimal.valueOf(10.0))),
				Arguments.of(new ProdutoRespostaDTO(2L, "Produto 2", "Descricao 2", BigDecimal.valueOf(20.0)),
						new ProdutoSolicitacaoDTO("Produto 2", "Descricao 2", BigDecimal.valueOf(20.0))),
				Arguments.of(new ProdutoRespostaDTO(3L, "Produto 3", "Descricao 3", BigDecimal.valueOf(30.0)),
						new ProdutoSolicitacaoDTO("Produto 3", "Descricao 3", BigDecimal.valueOf(30.0))));
	}

	@ParameterizedTest
	@MethodSource("produtoDTOProvider")
	@DisplayName("Dado um produto existente, quando buscar o produto, então ele deve ser retornado")
	void dadoProdutoExistente_quandoBuscarProduto_entaoDeveSerRetornado(ProdutoRespostaDTO produtoRespostaDTO) {
		Long id = produtoRespostaDTO.id();
		Produto produto = new Produto();
		when(buscaProdutoPorIdPortaEntrada.buscar(id)).thenReturn(produto);
		when(produtoEntradaMapeador.paraProdutoDTO(produto)).thenReturn(produtoRespostaDTO);

		ResponseEntity<ProdutoRespostaDTO> response = produtoControlador.buscaProdutoPorId(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(produtoRespostaDTO, response.getBody());
		verify(buscaProdutoPorIdPortaEntrada).buscar(id);
		verify(produtoEntradaMapeador).paraProdutoDTO(produto);
	}

	@ParameterizedTest
	@MethodSource("produtoDTOProvider")
	@DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
	void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo(ProdutoRespostaDTO produtoRespostaDTO,
			ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
		Produto produto = new Produto();
		Produto produtoPersistido = new Produto();
		when(produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO)).thenReturn(produto);
		when(insereProdutoPortaEntrada.inserir(produto)).thenReturn(produtoPersistido);
		when(produtoEntradaMapeador.paraProdutoDTO(produtoPersistido)).thenReturn(produtoRespostaDTO);

		ResponseEntity<ProdutoRespostaDTO> response = produtoControlador.insereProduto(produtoSolicitacaoDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(produtoRespostaDTO, response.getBody());
		verify(produtoEntradaMapeador).paraProduto(produtoSolicitacaoDTO);
		verify(insereProdutoPortaEntrada).inserir(produto);
		verify(produtoEntradaMapeador).paraProdutoDTO(produtoPersistido);
	}

	@ParameterizedTest
	@MethodSource("produtoDTOProvider")
	@DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
	void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado(ProdutoRespostaDTO produtoRespotaDTO,
			ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
		Long id = produtoRespotaDTO.id();
		Produto produto = new Produto();
		Produto produtoPersistido = new Produto();

		when(produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO)).thenReturn(produto);
		when(alteraProdutoPortaEntrada.alterar(id, produto)).thenReturn(produtoPersistido);
		when(produtoEntradaMapeador.paraProdutoDTO(produtoPersistido)).thenReturn(produtoRespotaDTO);

		ResponseEntity<ProdutoRespostaDTO> response = produtoControlador.alteraProduto(id, produtoSolicitacaoDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(produtoRespotaDTO, response.getBody());
		verify(produtoEntradaMapeador).paraProduto(produtoSolicitacaoDTO);
		verify(alteraProdutoPortaEntrada).alterar(id, produto);
		verify(produtoEntradaMapeador).paraProdutoDTO(produtoPersistido);
	}

	@ParameterizedTest
	@MethodSource("produtoDTOProvider")
	@DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
	void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido(ProdutoRespostaDTO produtoRespostaDTO) {
		Long id = produtoRespostaDTO.id();

		ResponseEntity<Void> response = produtoControlador.deletaProduto(id);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(deletaProdutoPortaEntrada).deleta(id);
	}
}
