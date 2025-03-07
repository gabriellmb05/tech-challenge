package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoSolicitacao;

public interface PedidoInsereUseCase {
    Pedido inserePedido(PedidoSolicitacao pedidoSolicitacao);
}
