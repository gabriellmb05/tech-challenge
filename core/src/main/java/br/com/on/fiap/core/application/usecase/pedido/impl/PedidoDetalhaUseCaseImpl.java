package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.application.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;

public class PedidoDetalhaUseCaseImpl implements PedidoDetalhaUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoDetalhaUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido detalhaPedido(String protocolo) {
        return pedidoGateway
                .detalhaPedido(protocolo)
                .orElseThrow(() -> new PedidoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo));
    }
}
