package br.com.on.fiap.core.domain.exception.message;

public enum MessageError {
    MSG_ERRO_CLIENTE_CPF_JA_CADASTRADO("msg-erro-cliente-cpf-ja-cadastrado"),
    MSG_ERRO_CLIENTE_EMAIL_JA_CADASTRADO("msg-erro-cliente-email-ja-cadastrado"),
    MSG_ERRO_CLIENTE_NAO_CADASTRADO("msg-erro-cliente-nao-cadastrado"),
    MSG_ERRO_CLIENTE_NAO_ENCONTRATO_PARA_ID("msg-erro-cliente-nao-encontrado-para-id"),
    MSG_ERRO_PRODUTO_CATEGORIA_NAO_CADASTRADA("msg-erro-produto-categoria-nao-cadastrada"),
    MSG_ERRO_PRODUTO_NAO_CADASTRADO("msg-erro-produto-nao-cadastrado"),
    MSG_ERRO_PRODUTO_JA_CADASTRADO("msg-erro-produto-ja-cadastrado"),
    MSG_ERRO_PRODUTO_NAO_ENCONTRADO("msg-erro-produto-nao-encontrado"),
    MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO("msg-erro-pedido-nao-encontrado-para-protocolo"),
    MSG_ERRO_PAGAMENTO_TIPO_PAGAMENTO_NAO_ENCONTRADO("msg-erro-pagamento-tipo-pagamento-nao-encontrado"),
    MSG_ERRO_PAGAMENTO_JA_REALIZADO_PARA_PEDIDO("msg-erro-pagamento-ja-realizado-para-pedido"),
    MSG_ERRO_INTEGRACAO_INESPERADO("msg-erro-integracao-inesperado");

    private final String mensagem;

    MessageError(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
