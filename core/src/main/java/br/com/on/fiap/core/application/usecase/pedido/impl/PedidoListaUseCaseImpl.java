package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import br.com.on.fiap.core.application.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.domain.Pedido;

public class PedidoListaUseCaseImpl implements PedidoListaUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoListaUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pagina<Pedido> buscarPedidosComFiltro(PedidoFiltroEntrada filtro, Paginacao paginacao) {
        return pedidoGateway.listarComFiltros(filtro, paginacao);
    }
}
