package br.com.on.fiap.hexagono.casosdeuso.excecoes;

public class ProdutoExistenteExcecao extends RuntimeException{

    public ProdutoExistenteExcecao(String message) {
        super(message);
    }
}
