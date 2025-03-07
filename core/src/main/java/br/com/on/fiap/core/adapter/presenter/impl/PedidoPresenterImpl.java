package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.domain.model.*;
import java.util.List;

public class PedidoPresenterImpl implements PedidoPresenter {
    @Override
    public PedidoResposta formatar(Pedido pedido) {
        return PedidoResposta.create(pedido);
    }

    @Override
    public Pagina<PedidoResposta> formatar(Pagina<Pedido> obj) {
        return obj.map(this::formatar);
    }

    @Override
    public PedidoDetalhadoResposta formatarDetalhado(Pedido pedido) {
        ClienteRespostaDTO cliente = ClienteRespostaDTO.fromDomain(pedido.getCliente());

        List<ProdutoResposta> produtos = pedido.getRelPedidoProdutos().stream()
                .map(PedidoProduto::getProduto)
                .map(ProdutoResposta::create)
                .toList();

        PagamentoResposta pagamento = PagamentoResposta.create(pedido.getPagamento());
        return PedidoDetalhadoResposta.create(pedido.getId(), cliente, pedido.getSituacao(), produtos, pagamento);
    }
}
