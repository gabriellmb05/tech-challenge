package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoDetalhadoResposta;
import br.com.on.fiap.core.domain.model.PedidoResposta;

public interface PedidoPresenter extends PaginacaoPresenter<Pedido, PedidoResposta> {
    PedidoResposta formatar(Pedido pedido);

    PedidoDetalhadoResposta formatarDetalhado(Pedido pedido);
}
