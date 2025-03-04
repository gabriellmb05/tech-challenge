package br.com.on.fiap.core.domain.exception;

public class ProdutoExistenteExcecao extends ExcecaoPadraoAplicacao {

    public ProdutoExistenteExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
