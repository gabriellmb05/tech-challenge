package br.com.on.fiap.core.adapter.gateway.impl;

import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.entity.PedidoFiltro;
import br.com.on.fiap.core.domain.entity.RelPedidoProduto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
        return pedidoDataSource.listarComFiltros(filtro, page);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        return pedidoDataSource.detalhaPedido(protocolo);
    }

    @Override
    public void salvaPedidoPagamento(Pedido pedido) {
        pedidoDataSource.salvaPedidoPagamento(pedido);
    }

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        return pedidoDataSource.salvaPedido(pedido);
    }

    @Override
    public void vincularPedido(List<RelPedidoProduto> pedidoProdutos) {
        pedidoDataSource.vincularPedido(pedidoProdutos);
    }
}
