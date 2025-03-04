package br.com.on.fiap.hexagono.usecase.pedido;

import br.com.on.fiap.hexagono.domain.entity.Pedido;

public interface PedidoAtualizaUseCase {
    Pedido atualizarPedido(String protocolo);
}
