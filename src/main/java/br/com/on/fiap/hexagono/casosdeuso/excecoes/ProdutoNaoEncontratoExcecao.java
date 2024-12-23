package br.com.on.fiap.hexagono.casosdeuso.excecoes;

public class ProdutoNaoEncontratoExcecao extends RuntimeException{

    public ProdutoNaoEncontratoExcecao(String message) {
        super(message);
    }
}
