package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.hexagono.excecao.ApplicationExcecaoPadrao;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ManipuladorExcecaoGlobal extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApplicationExcecaoPadrao.class)
	public ResponseEntity<ProblemDetail> applicationExcecaoPadrao(ApplicationExcecaoPadrao ex, WebRequest request) {
		ProblemDetail detalheProblema = this.createProblemDetail(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), null,
				null, request);
		return ResponseEntity.badRequest().body(detalheProblema);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex, WebRequest request) {
		ProblemDetail detalheProblema = this.createProblemDetail(ex, HttpStatus.INTERNAL_SERVER_ERROR,
				"Erro interno da aplicação", null, null, request);
		return ResponseEntity.internalServerError().body(detalheProblema);
	}
}