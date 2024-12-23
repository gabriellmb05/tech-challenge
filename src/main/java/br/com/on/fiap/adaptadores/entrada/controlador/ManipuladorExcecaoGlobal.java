package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.DetalheErroExcecaoDTO;
import br.com.on.fiap.hexagono.casosdeuso.excecoes.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.casosdeuso.excecoes.ProdutoNaoEncontratoExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ManipuladorExcecaoGlobal {

    @ExceptionHandler(ProdutoExistenteExcecao.class)
    public ResponseEntity<?> manipulaProdutoExistenteExcecao(ProdutoExistenteExcecao ex, WebRequest request) {
        DetalheErroExcecaoDTO errorDetails = new DetalheErroExcecaoDTO(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoNaoEncontratoExcecao.class)
    public ResponseEntity<?> manipulaProdutoNaoEncontradoExcecao(ProdutoNaoEncontratoExcecao ex, WebRequest request) {
        DetalheErroExcecaoDTO errorDetails = new DetalheErroExcecaoDTO(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        DetalheErroExcecaoDTO errorDetails = new DetalheErroExcecaoDTO(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
