package br.com.on.fiap.core.usecase.pedido;

import br.com.on.fiap.core.domain.entity.Pagamento;
import br.com.on.fiap.core.domain.entity.Pedido;

public interface PedidoInsereUseCase {
    Pedido inserir(Pedido pedido, Pagamento pagamento);
}
