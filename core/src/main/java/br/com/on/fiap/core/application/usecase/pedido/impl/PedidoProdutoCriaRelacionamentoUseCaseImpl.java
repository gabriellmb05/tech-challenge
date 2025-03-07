package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.usecase.pedido.PedidoProdutoCriaRelacionamentoUseCase;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoProduto;
import br.com.on.fiap.core.domain.model.Produto;
import java.util.List;
import java.util.Map;

public class PedidoProdutoCriaRelacionamentoUseCaseImpl implements PedidoProdutoCriaRelacionamentoUseCase {

    @Override
    public void criaRelacionamentoProdutoPedido(Pedido pedido, Map<Produto, Long> produtos) {
        List<PedidoProduto> pedidoProdutos = produtos.entrySet().stream()
                .map(produtoEntry -> {
                    Produto produto = produtoEntry.getKey();
                    return new PedidoProduto(produto, pedido, produtoEntry.getValue());
                })
                .toList();
        pedido.setRelPedidoProdutos(pedidoProdutos);
    }
}
