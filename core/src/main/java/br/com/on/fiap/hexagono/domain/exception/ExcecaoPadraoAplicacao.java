package br.com.on.fiap.hexagono.domain.exception;

import br.com.on.fiap.hexagono.domain.exception.message.MessageManager;

public abstract class ExcecaoPadraoAplicacao extends RuntimeException {

    protected ExcecaoPadraoAplicacao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
