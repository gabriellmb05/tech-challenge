package br.com.on.fiap.hexagono.domain.exception;

public class ClienteExistenteExcecao extends ExcecaoPadraoAplicacao {

    public ClienteExistenteExcecao(String chave, Object... args) {
        super(chave, args);
    }
}
