package br.com.on.fiap.hexagono.usecase.pedido.impl;

import br.com.on.fiap.hexagono.adapter.gateway.PedidoGateway;
import br.com.on.fiap.hexagono.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.exception.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;

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
