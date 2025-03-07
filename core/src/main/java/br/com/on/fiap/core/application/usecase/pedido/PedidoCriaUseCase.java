package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.entrada.pedido.PedidoEntrada;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoCriaUseCase {

    Pedido criaPedido(PedidoEntrada pedidoEntrada, Cliente cliente, Pagamento pagamento);
}
