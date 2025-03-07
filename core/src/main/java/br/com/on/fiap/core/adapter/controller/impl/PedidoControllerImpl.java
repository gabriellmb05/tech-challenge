package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.PedidoController;
import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.application.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.domain.model.*;

public class PedidoControllerImpl implements PedidoController {

    private final PedidoInsereUseCase pedidoInsereUseCase;
    private final PedidoListaUseCase pedidoListaUseCase;
    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PedidoAtualizaUseCase pedidoAtualizaUseCase;

    private final PedidoPresenter pedidoPresenter;

    public PedidoControllerImpl(
            PedidoInsereUseCase pedidoInsereUseCase,
            PedidoListaUseCase pedidoListaUseCase,
            PedidoDetalhaUseCase pedidoDetalhaUseCase,
            PedidoAtualizaUseCase pedidoAtualizaUseCase,
            PedidoPresenter pedidoPresenter) {
        this.pedidoInsereUseCase = pedidoInsereUseCase;
        this.pedidoListaUseCase = pedidoListaUseCase;
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.pedidoAtualizaUseCase = pedidoAtualizaUseCase;
        this.pedidoPresenter = pedidoPresenter;
    }

    @Override
    public PedidoResposta inserePedido(PedidoSolicitacao pedidoSolicitacao) {
        Pedido pedido = pedidoInsereUseCase.inserePedido(pedidoSolicitacao);
        return pedidoPresenter.formatar(pedido);
    }

    @Override
    public Pagina<PedidoResposta> listarPedidoComFiltro(PedidoFiltroEntrada filtro, Paginacao paginacao) {
        Pagina<Pedido> pedidoPagina = pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacao);
        return pedidoPresenter.formatar(pedidoPagina);
    }

    @Override
    public PedidoDetalhadoResposta detalhaPedido(String protocolo) {
        Pedido pedido = pedidoDetalhaUseCase.detalhaPedido(protocolo);
        return pedidoPresenter.formatarDetalhado(pedido);
    }

    @Override
    public PedidoResposta atualizarPedido(String protocolo) {
        Pedido pedido = pedidoAtualizaUseCase.atualizarPedido(protocolo);
        return pedidoPresenter.formatar(pedido);
    }
}
