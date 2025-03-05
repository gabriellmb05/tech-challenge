package br.com.on.fiap.infrastructure.exception;

import br.com.on.fiap.core.application.exception.message.MessageManager;

public class IntegracaoPagamentoExcecao extends RuntimeException {
    public IntegracaoPagamentoExcecao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
