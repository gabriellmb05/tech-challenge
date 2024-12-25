package br.com.on.fiap.hexagono.casosdeuso.excecao;

public class ProdutoNaoEncontratoExcecao extends RuntimeException{

    public ProdutoNaoEncontratoExcecao(String message) {
        super(message);
    }
}
