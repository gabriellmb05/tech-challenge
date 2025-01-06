package br.com.on.fiap.hexagono.excecao.message;

public enum MessageError {
  MSG_ERR_CLIENTE_JA_CADASTRADO("cliente-cadastrado"),
  MSG_ERR_CLIENTE_NAO_CADASTRADO("cliente-nao-cadastrado");

  private final String mensagem;

  MessageError(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }
}
