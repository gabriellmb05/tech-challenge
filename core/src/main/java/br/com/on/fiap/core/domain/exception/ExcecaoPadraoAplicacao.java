package br.com.on.fiap.core.domain.exception;

import br.com.on.fiap.core.domain.exception.message.MessageManager;

public abstract class ExcecaoPadraoAplicacao extends RuntimeException {

    protected ExcecaoPadraoAplicacao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
