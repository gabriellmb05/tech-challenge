package br.com.on.fiap.hexagono.excecao;

import java.text.MessageFormat;

public class ProdutoExistenteExcecao extends RuntimeException {

	public ProdutoExistenteExcecao(String nomeProduto) {
		super(MessageFormat.format("Produto ({0}) já cadastrado", nomeProduto));
	}
}