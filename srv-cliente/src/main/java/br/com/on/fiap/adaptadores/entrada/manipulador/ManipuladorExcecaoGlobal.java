package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.hexagono.excecao.ApplicationExcecaoPadrao;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorExcecaoGlobal {

	@ExceptionHandler(ApplicationExcecaoPadrao.class)
	public ResponseEntity<DetalhesErrosGerais> applicationExcecaoPadrao(ApplicationExcecaoPadrao ex,
			WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<DetalhesErrosGerais> handleGlobalException(Exception ex, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(Collections.singletonList(ex.getMessage())).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalhesErrosGerais> handleValidationExceptions(MethodArgumentNotValidException ex,
			WebRequest request) {
		return ResponseEntity.internalServerError()
				.body(DetalhesErrosGerais.builder().statusCode(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now()).details(request.getDescription(false))
						.errors(ex.getBindingResult().getFieldErrors().stream()
								.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
						.build());
	}
}
