package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.hexagono.excecao.ApplicationExcecaoPadrao;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManipuladorExcecaoGlobal extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApplicationExcecaoPadrao.class)
	public ResponseEntity<ProblemDetail> applicationExcecaoPadrao(ApplicationExcecaoPadrao ex, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(createProblemDetail(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), null, null, request));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex, WebRequest request) {
		return ResponseEntity.internalServerError().body(createProblemDetail(ex, HttpStatus.INTERNAL_SERVER_ERROR,
				"Erro interno da aplicação", null, null, request));
	}

}
