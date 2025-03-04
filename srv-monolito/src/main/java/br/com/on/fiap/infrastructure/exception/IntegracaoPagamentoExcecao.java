package br.com.on.fiap.infrastructure.exception;

import br.com.on.fiap.hexagono.domain.exception.message.MessageManager;

public class IntegracaoPagamentoExcecao extends RuntimeException {
    public IntegracaoPagamentoExcecao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
