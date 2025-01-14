package br.com.on.fiap.adaptadores.entrada.manipulador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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

		ResponseEntity<ProblemDetail> response = manipuladorExcecaoGlobal.manipulaProdutoExistenteExcecao(ex, request);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Produto (Produto já existe) já cadastrado", response.getBody().getDetail());
	}

	@Test
	@DisplayName("Dado uma exceção ProdutoNaoEncontratoExcecao, quando manipulada, então deve retornar NOT_FOUND")
	void dadoProdutoNaoEncontratoExcecao_quandoManipulada_entaoDeveRetornarNotFound() {
		ProdutoNaoEncontratoExcecao ex = new ProdutoNaoEncontratoExcecao(0L);

		ResponseEntity<ProblemDetail> response = manipuladorExcecaoGlobal.manipulaProdutoNaoEncontradoExcecao(ex,
				request);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Produto (0) não encontrado", response.getBody().getDetail());
	}

	@Test
	@DisplayName("Dado uma exceção genérica, quando manipulada, então deve retornar INTERNAL_SERVER_ERROR")
	void dadoExcecaoGenerica_quandoManipulada_entaoDeveRetornarInternalServerError() {
		Exception ex = new Exception("Erro interno da aplicação");

		ResponseEntity<ProblemDetail> response = manipuladorExcecaoGlobal.handleGlobalException(ex, request);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Erro interno da aplicação", response.getBody().getDetail());
	}

    @Test
    @DisplayName("Dado uma exceção CategoriaNaoEncontradaExcecao, quando manipulada, então deve retornar BAD_REQUEST")
    void dadoCategoriaNaoEncontradaExcecao_quandoManipulada_entaoDeveRetornarBadRequest() {
        CategoriaNaoEncontradaExcecao ex = new CategoriaNaoEncontradaExcecao("teste");

        ResponseEntity<ProblemDetail> response = manipuladorExcecaoGlobal.manipulaCategoriaNaoEncontradaExcecao(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Categoria (teste) não encontrada", response.getBody().getDetail());
    }
}
