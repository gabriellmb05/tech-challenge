package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.PedidoController;
import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.application.dto.entrada.pedido.PedidoEntrada;
import br.com.on.fiap.core.application.dto.filtro.pedido.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.pedido.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.pedido.PedidoResposta;
import br.com.on.fiap.core.application.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.domain.Pedido;

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
    public PedidoResposta inserePedido(PedidoEntrada pedidoEntrada) {
        Pedido pedido = pedidoInsereUseCase.inserePedido(pedidoEntrada);
        return pedidoPresenter.formatar(pedido);
    }

    @Override
    public PaginaResposta<PedidoResposta> listarPedidoComFiltro(
            PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta) {
        PaginaResposta<Pedido> pedidoPaginaResposta =
                pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta);
        return pedidoPresenter.formatar(pedidoPaginaResposta);
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
