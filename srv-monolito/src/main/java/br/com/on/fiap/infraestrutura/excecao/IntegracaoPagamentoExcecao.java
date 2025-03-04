package br.com.on.fiap.infraestrutura.excecao;

import br.com.on.fiap.hexagono.excecao.message.MessageManager;

public class IntegracaoPagamentoExcecao extends RuntimeException {
    public IntegracaoPagamentoExcecao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
