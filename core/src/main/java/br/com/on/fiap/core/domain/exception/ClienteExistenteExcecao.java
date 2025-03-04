package br.com.on.fiap.core.domain.exception;

public class ClienteExistenteExcecao extends ExcecaoPadraoAplicacao {

    public ClienteExistenteExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
