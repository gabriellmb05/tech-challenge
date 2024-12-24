package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.hexagono.casosdeuso.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.casosdeuso.excecao.ProdutoNaoEncontratoExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ManipuladorExcecaoGlobal extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProdutoExistenteExcecao.class)
    public ResponseEntity<?> manipulaProdutoExistenteExcecao(ProdutoExistenteExcecao ex, WebRequest request) {
        ProblemDetail detalheṔroblema = this.createProblemDetail(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), (String) null, (Object[]) null, request);
        return new ResponseEntity<>(detalheṔroblema, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoNaoEncontratoExcecao.class)
    public ResponseEntity<?> manipulaProdutoNaoEncontradoExcecao(ProdutoNaoEncontratoExcecao ex, WebRequest request) {
        ProblemDetail detalheṔroblema = this.createProblemDetail(ex, HttpStatus.NOT_FOUND, ex.getMessage(), (String) null, (Object[]) null, request);
        return new ResponseEntity<>(detalheṔroblema, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ProblemDetail detalheṔroblema = this.createProblemDetail(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno da aplicação", (String) null, (Object[]) null, request);
        return new ResponseEntity<>(detalheṔroblema, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
