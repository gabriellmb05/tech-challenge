package br.com.on.fiap.hexagono.excecao.message;

public enum MessageError {

	MSG_ERRO_CPF_JA_CADASTRADO("cpf-ja-cadastrado"), MSG_ERRO_EMAIL_JA_CADASTRADO(
			"email-ja-cadastrado"), MSG_ERRO_CLIENTE_NAO_CADASTRADO(
					"cliente-nao-cadastrado"), MSG_ERRO_CATEGORIA_NAO_CADASTRADA(
							"categoria-nao-cadastrada"), MSG_ERRO_PRODUTO_NAO_CADASTRADO(
									"produto-nao-cadastrado"), MSG_ERRO_PRODUTO_JA_CADASTRADO(
											"produto-ja-cadastrado"), MSG_CLIENTE_NAO_ENCONTRATO_PARA_ID(
													"cliente-nao-encontrado-por-id"), MSG_PRODUTOS_NAO_ENCONTRADOS(
															"produtos-nao-encontrados"), MSG_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO(
																	"pedido-nao-encontrado-para-id");

	private final String mensagem;

	MessageError(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
