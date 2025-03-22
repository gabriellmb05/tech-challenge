package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.domain.Pedido;
import java.util.Optional;

public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoDataSource pedidoDataSource;

    public PedidoGatewayImpl(PedidoDataSource pedidoDataSource) {
        this.pedidoDataSource = pedidoDataSource;
    }

    @Override
    public Optional<Pedido> atualizarPedido(String protocolo) {
        return pedidoDataSource.atualizarPedido(protocolo);
    }

    @Override
    public PaginaResposta<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta) {
        return pedidoDataSource.listarComFiltros(filtro, paginacaoResposta);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        return pedidoDataSource.detalhaPedido(protocolo);
    }

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        return pedidoDataSource.salvaPedido(pedido);
    }

    @Override
    public PaginaResposta<Pedido> listarComFiltros(PaginacaoResposta paginacaoResposta) {
        return pedidoDataSource.listarComFiltros(paginacaoResposta);
    }
}
