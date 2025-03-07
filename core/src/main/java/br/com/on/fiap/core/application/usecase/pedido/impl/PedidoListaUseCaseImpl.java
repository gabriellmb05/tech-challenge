package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.dto.filtro.pedido.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.application.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.domain.Pedido;

public class PedidoListaUseCaseImpl implements PedidoListaUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoListaUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public PaginaResposta<Pedido> buscarPedidosComFiltro(
            PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta) {
        return pedidoGateway.listarComFiltros(filtro, paginacaoResposta);
    }
}
