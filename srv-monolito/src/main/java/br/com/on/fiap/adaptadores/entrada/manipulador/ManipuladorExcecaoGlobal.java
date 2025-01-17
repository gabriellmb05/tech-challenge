package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.hexagono.excecao.*;
import java.time.LocalDateTime;
import java.util.Collections;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ManipuladorExcecaoGlobal {

	@ExceptionHandler(ExcecaoPadraoAplicacao.class)
	public ResponseEntity<DetalhesErrosGerais> handleApplicationExcecaoPadrao(ExcecaoPadraoAplicacao ex,
			WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalhesErrosGerais> handleValidationExceptions(MethodArgumentNotValidException ex,
			WebRequest request) {
		return ResponseEntity.badRequest().body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now()).details(request.getDescription(false)).errors(ex.getBindingResult()
						.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
				.build());
	}

	@ExceptionHandler(CategoriaNaoEncontradaExcecao.class)
	public ResponseEntity<DetalhesErrosGerais> handleCategoriaNaoEncontradaExcecao(CategoriaNaoEncontradaExcecao ex,
			WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}

	@ExceptionHandler(ProdutoExistenteExcecao.class)
	public ResponseEntity<DetalhesErrosGerais> handleProdutoExistenteExcecao(ProdutoExistenteExcecao ex,
			WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}

	@ExceptionHandler(ClienteExistenteExcecao.class)
	public ResponseEntity<DetalhesErrosGerais> handleClienteExistenteExcecao(ClienteExistenteExcecao ex,
			WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}

	@ExceptionHandler(ProdutoNaoEncontradoExcecao.class)
	public ResponseEntity<DetalhesErrosGerais> handleProdutoNaoEncontradoExcecao(ProdutoNaoEncontradoExcecao ex,
			WebRequest request) {
		return new ResponseEntity<>(DetalhesErrosGerais.builder().statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now()).details(request.getDescription(false))
				.errors(Collections.singletonList(ex.getMessage())).build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClienteNaoEncontradoExcecao.class)
	public ResponseEntity<DetalhesErrosGerais> handleClienteNaoEncontradoExcecao(ClienteNaoEncontradoExcecao ex,
			WebRequest request) {
		return new ResponseEntity<>(DetalhesErrosGerais.builder().statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now()).details(request.getDescription(false))
				.errors(Collections.singletonList(ex.getMessage())).build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<DetalhesErrosGerais> handleGlobalException(Exception ex, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}
}
