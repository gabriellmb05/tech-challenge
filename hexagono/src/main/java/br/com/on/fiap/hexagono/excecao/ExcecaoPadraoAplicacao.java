package br.com.on.fiap.hexagono.excecao;

import br.com.on.fiap.hexagono.excecao.message.MessageManager;

public abstract class ExcecaoPadraoAplicacao extends RuntimeException {

	protected ExcecaoPadraoAplicacao(String chave, Object... args) {
		super(MessageManager.getMessage(chave, args));
	}
}
