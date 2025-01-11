package br.com.on.fiap.hexagono.excecao;

import br.com.on.fiap.hexagono.excecao.message.MessageManager;

public class ApplicationExcecaoPadrao extends RuntimeException {

	public ApplicationExcecaoPadrao(String chave, Object... args) {
		super(MessageManager.getMessage(chave, args));
	}

}
