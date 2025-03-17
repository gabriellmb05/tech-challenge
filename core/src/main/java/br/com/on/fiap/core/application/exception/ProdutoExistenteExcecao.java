package br.com.on.fiap.core.application.exception;

public class ProdutoExistenteExcecao extends ExcecaoPadraoAplicacao {

    public ProdutoExistenteExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
