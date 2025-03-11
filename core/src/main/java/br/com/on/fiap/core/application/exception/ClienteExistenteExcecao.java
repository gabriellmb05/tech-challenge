package br.com.on.fiap.core.application.exception;

public class ClienteExistenteExcecao extends ExcecaoPadraoAplicacao {

    public ClienteExistenteExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
