package br.com.on.fiap.hexagono.application.usecase.pedido;

import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.domain.entity.Pedido;

public interface PedidoInsereUseCase {
    Pedido inserir(Pedido pedido, Pagamento pagamento);
}
