package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorExcecaoGlobal extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProdutoExistenteExcecao.class)
	public ResponseEntity<ProblemDetail> manipulaProdutoExistenteExcecao(ProdutoExistenteExcecao ex,
			WebRequest request) {
		return createResponseEntity(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), request);
	}

	@ExceptionHandler(ProdutoNaoEncontratoExcecao.class)
	public ResponseEntity<ProblemDetail> manipulaProdutoNaoEncontradoExcecao(ProdutoNaoEncontratoExcecao ex,
			WebRequest request) {
		return createResponseEntity(ex, HttpStatus.NOT_FOUND, ex.getMessage(), request);
	}

	@ExceptionHandler(CategoriaNaoEncontradaExcecao.class)
	public ResponseEntity<ProblemDetail> manipulaCategoriaNaoEncontradaExcecao(CategoriaNaoEncontradaExcecao ex,
			WebRequest request) {
		return createResponseEntity(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex, WebRequest request) {
		return createResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno da aplicação", request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage,
						(existing, replacement) -> existing));
		ProblemDetail detalheProblema = ProblemDetail.forStatusAndDetail(status,
				"Erro de validação referente a entrada dos dados");
		detalheProblema.setProperty("errors", errors);
		return new ResponseEntity<>(detalheProblema, headers, status);
	}

	private ResponseEntity<ProblemDetail> createResponseEntity(Exception ex, HttpStatus status, String message,
			WebRequest request) {
		ProblemDetail detalheProblema = this.createProblemDetail(ex, status, message, null, null, request);
		return new ResponseEntity<>(detalheProblema, status);
	}
}
