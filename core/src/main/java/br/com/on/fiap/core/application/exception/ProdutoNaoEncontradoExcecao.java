package br.com.on.fiap.core.application.exception;

public class ProdutoNaoEncontradoExcecao extends ExcecaoPadraoAplicacao {

    public ProdutoNaoEncontradoExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
