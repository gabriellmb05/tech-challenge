package br.com.on.fiap.hexagono.excecao.message;

public enum MessageError {

	MSG_ERRO_CPF_JA_CADASTRADO("cpf-ja-cadastrado"), MSG_ERRO_EMAIL_JA_CADASTRADO(
			"email-ja-cadastrado"), MSG_ERRO_CLIENTE_NAO_CADASTRADO("cliente-nao-cadastrado");

	private final String mensagem;

	MessageError(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
