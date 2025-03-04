package br.com.on.fiap.infrastructure.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.on.fiap.hexagono.domain.exception.*;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
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
class RestExceptionHandlerTest {

    @Mock
    private WebRequest request;

    @InjectMocks
    private RestExceptionHandler exceptionHandler;

    @Test
    @DisplayName("Dado uma exceção ProdutoExistenteExcecao, quando manipulada, então deve retornar BAD_REQUEST")
    void dadoProdutoExistenteExcecao_quandoManipulada_entaoDeveRetornarBadRequest() {
        ProdutoExistenteExcecao ex =
                new ProdutoExistenteExcecao(MessageError.MSG_ERRO_PRODUTO_JA_CADASTRADO.getMensagem(), 1L);

        ResponseEntity<DetalhesErrosGeraisDTO> response = exceptionHandler.handleProdutoExistenteExcecao(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(
                "O Produto (1) já foi cadastrado",
                response.getBody().getErrors().getFirst());
    }

    @Test
    @DisplayName("Dado uma exceção ClienteExistenteExcecao, quando cpf já existente, então deve retornar BAD_REQUEST")
    void dadoClienteExistenteExcecao_quandoManipuladaCpf_entaoDeveRetornarBadRequest() {
        ClienteExistenteExcecao ex = new ClienteExistenteExcecao(
                MessageError.MSG_ERRO_CLIENTE_CPF_JA_CADASTRADO.getMensagem(), "75864522023");

        ResponseEntity<DetalhesErrosGeraisDTO> response = exceptionHandler.handleClienteExistenteExcecao(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(
                "O CPF de número 75864522023 já foi utilizado",
                response.getBody().getErrors().getFirst());
    }

    @Test
    @DisplayName("Dado uma exceção ClienteExistenteExcecao, quando email já existente, então deve retornar BAD_REQUEST")
    void dadoClienteExistenteExcecao_quandoManipuladaEmail_entaoDeveRetornarBadRequest() {
        ClienteExistenteExcecao ex = new ClienteExistenteExcecao(
                MessageError.MSG_ERRO_CLIENTE_EMAIL_JA_CADASTRADO.getMensagem(), "teste@gmail.com");

        ResponseEntity<DetalhesErrosGeraisDTO> response = exceptionHandler.handleClienteExistenteExcecao(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(
                "O E-mail teste@gmail.com já foi utilizado",
                response.getBody().getErrors().getFirst());
    }

    @Test
    @DisplayName("Dado uma exceção ProdutoNaoEncontradoExcecao, quando manipulada, então deve retornar NOT_FOUND")
    void dadoProdutoNaoEncontradoExcecao_quandoManipulada_entaoDeveRetornarNotFound() {
        ProdutoNaoEncontradoExcecao ex =
                new ProdutoNaoEncontradoExcecao(MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), 0L);

        ResponseEntity<DetalhesErrosGeraisDTO> response =
                exceptionHandler.handleProdutoNaoEncontradoExcecao(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(
                "Produto (0) não encontrado", response.getBody().getErrors().getFirst());
    }

    @Test
    @DisplayName("Dado uma exceção ClienteNaoEncontradoExcecao, quando manipulada, então deve retornar NOT_FOUND")
    void dadoClienteNaoEncontradoExcecao_quandoManipulada_entaoDeveRetornarNotFound() {
        ClienteNaoEncontradoExcecao ex = new ClienteNaoEncontradoExcecao(
                MessageError.MSG_ERRO_CLIENTE_NAO_CADASTRADO.getMensagem(), "75864522023");

        ResponseEntity<DetalhesErrosGeraisDTO> response =
                exceptionHandler.handleClienteNaoEncontradoExcecao(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(
                "Não foi encontrado Cliente para o cpf: 75864522023",
                response.getBody().getErrors().getFirst());
    }

    @Test
    @DisplayName("Dado uma exceção genérica, quando manipulada, então deve retornar INTERNAL_SERVER_ERROR")
    void dadoExcecaoGenerica_quandoManipulada_entaoDeveRetornarInternalServerError() {
        Exception ex = new Exception("Erro interno da aplicação");

        ResponseEntity<DetalhesErrosGeraisDTO> response = exceptionHandler.handleGlobalException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro interno da aplicação", response.getBody().getErrors().getFirst());
    }

    @Test
    @DisplayName("Dado uma exceção CategoriaNaoEncontradaExcecao, quando manipulada, então deve retornar BAD_REQUEST")
    void dadoCategoriaNaoEncontradaExcecao_quandoManipulada_entaoDeveRetornarBadRequest() {
        CategoriaNaoEncontradaExcecao ex = new CategoriaNaoEncontradaExcecao(
                MessageError.MSG_ERRO_PRODUTO_CATEGORIA_NAO_CADASTRADA.getMensagem(), 1L);

        ResponseEntity<DetalhesErrosGeraisDTO> response =
                exceptionHandler.handleCategoriaNaoEncontradaExcecao(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(
                "Categoria (1) não encontrada", response.getBody().getErrors().getFirst());
    }
}
