package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.Produto;
import java.util.Map;

public interface PedidoProdutoCriaRelacionamentoUseCase {

    void criaRelacionamentoProdutoPedido(Pedido pedido, Map<Produto, Long> produtos);
}
