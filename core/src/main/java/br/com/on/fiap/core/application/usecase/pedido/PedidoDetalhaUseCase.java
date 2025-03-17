package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.domain.Pedido;

public interface PedidoDetalhaUseCase {
    Pedido detalhaPedido(String protocolo);
}
