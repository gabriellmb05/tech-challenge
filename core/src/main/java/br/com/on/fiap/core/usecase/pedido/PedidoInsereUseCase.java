package br.com.on.fiap.core.usecase.pedido;

import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.Pedido;

public interface PedidoInsereUseCase {
    Pedido inserir(Pedido pedido, Pagamento pagamento);
}
