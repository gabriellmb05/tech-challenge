package br.com.on.fiap.adapter.infrastructure.handler;

import br.com.on.fiap.core.application.exception.*;
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
public class RestExceptionHandler {

    @ExceptionHandler(ExcecaoPadraoAplicacao.class)
    public ResponseEntity<ErrorApi> handleApplicationExcecaoPadrao(ExcecaoPadraoAplicacao ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorApi.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorApi.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(ex.getBindingResult().getFieldErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .toList())
                        .build());
    }

    @ExceptionHandler(CategoriaNaoEncontradaExcecao.class)
    public ResponseEntity<ErrorApi> handleCategoriaNaoEncontradaExcecao(
            CategoriaNaoEncontradaExcecao ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorApi.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(ProdutoExistenteExcecao.class)
    public ResponseEntity<ErrorApi> handleProdutoExistenteExcecao(ProdutoExistenteExcecao ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorApi.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(ClienteExistenteExcecao.class)
    public ResponseEntity<ErrorApi> handleClienteExistenteExcecao(ClienteExistenteExcecao ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorApi.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(ProdutoNaoEncontradoExcecao.class)
    public ResponseEntity<ErrorApi> handleProdutoNaoEncontradoExcecao(
            ProdutoNaoEncontradoExcecao ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorApi.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteNaoEncontradoExcecao.class)
    public ResponseEntity<ErrorApi> handleClienteNaoEncontradoExcecao(
            ClienteNaoEncontradoExcecao ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorApi.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleGlobalException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorApi.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(request.getDescription(false))
                        .errors(Collections.singletonList(ex.getMessage()))
                        .build());
    }
}
