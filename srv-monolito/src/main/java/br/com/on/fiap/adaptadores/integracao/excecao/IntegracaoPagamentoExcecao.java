package br.com.on.fiap.adaptadores.integracao.excecao;

import br.com.on.fiap.hexagono.usecases.excecao.message.MessageManager;

public class IntegracaoPagamentoExcecao extends RuntimeException {
    public IntegracaoPagamentoExcecao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
