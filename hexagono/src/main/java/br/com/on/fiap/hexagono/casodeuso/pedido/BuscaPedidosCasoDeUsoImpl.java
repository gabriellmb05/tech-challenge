package br.com.on.fiap.hexagono.casodeuso.pedido;

import br.com.on.fiap.hexagono.adaptadores.gateways.PedidoGateway;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BuscaPedidosCasoDeUsoImpl implements BuscaPedidosCasoDeUso {

    private final PedidoGateway pedidoGateway;

    public BuscaPedidosCasoDeUsoImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable) {
        return pedidoGateway.listarComFiltros(filtro, pageable);
    }
}
