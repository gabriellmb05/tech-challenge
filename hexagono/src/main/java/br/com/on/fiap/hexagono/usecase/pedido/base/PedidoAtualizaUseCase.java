package br.com.on.fiap.hexagono.usecase.pedido.base;

import br.com.on.fiap.hexagono.domain.entity.Pedido;

public interface PedidoAtualizaUseCase {
    Pedido atualizarPedido(String protocolo);
}
