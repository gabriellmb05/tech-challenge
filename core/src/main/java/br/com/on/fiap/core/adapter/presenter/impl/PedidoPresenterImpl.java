package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.application.dto.resposta.*;
import br.com.on.fiap.core.domain.Pedido;
import java.util.List;

public class PedidoPresenterImpl implements PedidoPresenter {
    @Override
    public PedidoResposta formatar(Pedido pedido) {
        return PedidoResposta.create(pedido);
    }

    @Override
    public PaginaResposta<PedidoResposta> formatar(PaginaResposta<Pedido> obj) {
        return obj.map(this::formatar);
    }

    @Override
    public PedidoDetalhadoResposta formatarDetalhado(Pedido pedido) {
        ClienteResposta cliente = ClienteResposta.fromDomain(pedido.getCliente());

        List<ProdutoQuantidade> produtos = pedido.getRelPedidoProdutos().stream()
                .map(ProdutoQuantidade::create)
                .toList();

        PagamentoResposta pagamento = PagamentoResposta.create(pedido.getPagamento());
        return PedidoDetalhadoResposta.create(pedido.getId(), cliente, pedido.getSituacao(), produtos, pagamento);
    }
}
