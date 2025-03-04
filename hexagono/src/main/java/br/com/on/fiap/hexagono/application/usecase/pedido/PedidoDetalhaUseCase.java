package br.com.on.fiap.hexagono.application.usecase.pedido;

import br.com.on.fiap.hexagono.domain.entity.Pedido;

public interface PedidoDetalhaUseCase {
    Pedido detalhaPedido(String protocolo);
}
