package br.com.on.fiap.adaptadores.entrada.manipulador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
class ManipuladorExcecaoGlobalTest {

	@Mock
	private WebRequest request;

	@InjectMocks
	private ManipuladorExcecaoGlobal manipuladorExcecaoGlobal;

	@Test
	@DisplayName("Dado uma exceção ProdutoExistenteExcecao, quando manipulada, então deve retornar BAD_REQUEST")
	void dadoProdutoExistenteExcecao_quandoManipulada_entaoDeveRetornarBadRequest() {
		ProdutoExistenteExcecao ex = new ProdutoExistenteExcecao("Produto já existe");

		ResponseEntity<DetalhesErrosGerais> response = manipuladorExcecaoGlobal.handleProdutoExistenteExcecao(ex,
				request);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Produto (Produto já existe) já cadastrado", response.getBody().getErrors().getFirst());
	}

	@Test
	@DisplayName("Dado uma exceção ProdutoNaoEncontratoExcecao, quando manipulada, então deve retornar NOT_FOUND")
	void dadoProdutoNaoEncontratoExcecao_quandoManipulada_entaoDeveRetornarNotFound() {
		ProdutoNaoEncontratoExcecao ex = new ProdutoNaoEncontratoExcecao(0L);

		ResponseEntity<DetalhesErrosGerais> response = manipuladorExcecaoGlobal.handleProdutoNaoEncontradoExcecao(ex,
				request);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Produto (0) não encontrado", response.getBody().getErrors().getFirst());
	}

	@Test
	@DisplayName("Dado uma exceção genérica, quando manipulada, então deve retornar INTERNAL_SERVER_ERROR")
	void dadoExcecaoGenerica_quandoManipulada_entaoDeveRetornarInternalServerError() {
		Exception ex = new Exception("Erro interno da aplicação");

		ResponseEntity<DetalhesErrosGerais> response = manipuladorExcecaoGlobal.handleGlobalException(ex, request);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Erro interno da aplicação", response.getBody().getErrors().getFirst());
	}
}
