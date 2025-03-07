package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoInsereUseCase {
    Pedido inserePedido(PedidoEntrada pedidoEntrada);
}
