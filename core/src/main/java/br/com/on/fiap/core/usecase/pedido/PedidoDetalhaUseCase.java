package br.com.on.fiap.core.usecase.pedido;

import br.com.on.fiap.core.domain.model.Pedido;

public interface PedidoDetalhaUseCase {
    Pedido detalhaPedido(String protocolo);
}
