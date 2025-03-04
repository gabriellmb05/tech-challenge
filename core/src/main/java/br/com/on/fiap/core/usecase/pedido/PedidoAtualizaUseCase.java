package br.com.on.fiap.core.usecase.pedido;

import br.com.on.fiap.core.domain.entity.Pedido;

public interface PedidoAtualizaUseCase {
    Pedido atualizarPedido(String protocolo);
}
