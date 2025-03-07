package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.pedido.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.pedido.PedidoResposta;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoPresenter extends PaginacaoPresenter<Pedido, PedidoResposta> {
    PedidoResposta formatar(Pedido pedido);

    PedidoDetalhadoResposta formatarDetalhado(Pedido pedido);
}
