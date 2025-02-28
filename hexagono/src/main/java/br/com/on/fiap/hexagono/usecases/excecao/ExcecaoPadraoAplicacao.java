package br.com.on.fiap.hexagono.usecases.excecao;

import br.com.on.fiap.hexagono.usecases.excecao.message.MessageManager;

public abstract class ExcecaoPadraoAplicacao extends RuntimeException {

    protected ExcecaoPadraoAplicacao(String chave, Object... args) {
        super(MessageManager.getMessage(chave, args));
    }
}
