package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.application.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.application.usecase.pedido.PedidoAtualizaUseCase;

public class PedidoAtualizaUseCaseImpl implements PedidoAtualizaUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoAtualizaUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido atualizarPedido(String protocolo) {
        return pedidoGateway
                .atualizarPedido(protocolo)
                .orElseThrow(() -> new PedidoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo));
    }
}
