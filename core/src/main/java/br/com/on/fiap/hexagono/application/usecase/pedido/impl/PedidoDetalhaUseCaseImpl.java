package br.com.on.fiap.hexagono.application.usecase.pedido.impl;

import br.com.on.fiap.hexagono.adapter.gateway.PedidoGateway;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;

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
