package br.com.on.fiap.hexagono.usecase.pedido;

import br.com.on.fiap.hexagono.adapter.gateway.PedidoGateway;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.entity.PedidoFiltro;
import br.com.on.fiap.hexagono.usecase.pedido.base.PedidoBuscaUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PedidoBuscaUseCaseImpl implements PedidoBuscaUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoBuscaUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable) {
        return pedidoGateway.listarComFiltros(filtro, pageable);
    }
}
