package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.Produto;
import java.util.Map;

public interface PedidoProdutoCriaRelacionamentoUseCase {

    void criaRelacionamentoProdutoPedido(Pedido pedido, Map<Produto, Long> produtos);
}
