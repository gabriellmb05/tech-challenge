package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoSolicitacao;

public interface PedidoCriaUseCase {

    Pedido criaPedido(PedidoSolicitacao pedidoSolicitacao, Cliente cliente, Pagamento pagamento);
}
