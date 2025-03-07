package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.entrada.PedidoSolicitacao;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoInsereUseCase {
    Pedido inserePedido(PedidoSolicitacao pedidoSolicitacao);
}
