package br.com.on.fiap.hexagono.excecao;

public class ProdutoNaoEncontratoExcecao extends RuntimeException {

    public ProdutoNaoEncontratoExcecao(String message) {
        super(message);
    }
}