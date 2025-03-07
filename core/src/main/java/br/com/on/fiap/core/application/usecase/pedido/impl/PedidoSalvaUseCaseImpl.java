package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.application.usecase.pedido.PedidoSalvaUseCase;
import br.com.on.fiap.core.domain.Pedido;

public class PedidoSalvaUseCaseImpl implements PedidoSalvaUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoSalvaUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        return pedidoGateway.salvaPedido(pedido);
    }
}
