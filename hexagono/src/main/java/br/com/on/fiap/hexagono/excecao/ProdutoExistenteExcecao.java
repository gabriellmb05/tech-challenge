package br.com.on.fiap.hexagono.excecao;

public class ProdutoExistenteExcecao extends RuntimeException {

    public ProdutoExistenteExcecao(String message) {
        super(message);
    }
}