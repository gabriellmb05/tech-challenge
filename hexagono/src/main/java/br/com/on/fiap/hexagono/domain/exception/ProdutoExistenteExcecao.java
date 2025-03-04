package br.com.on.fiap.hexagono.domain.exception;

public class ProdutoExistenteExcecao extends ExcecaoPadraoAplicacao {

    public ProdutoExistenteExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
