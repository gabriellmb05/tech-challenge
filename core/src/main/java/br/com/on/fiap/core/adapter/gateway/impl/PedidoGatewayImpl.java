package br.com.on.fiap.core.adapter.gateway.impl;

import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
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
    public Pagina<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, Paginacao paginacao) {
        return pedidoDataSource.listarComFiltros(filtro, paginacao);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        return pedidoDataSource.detalhaPedido(protocolo);
    }

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        return pedidoDataSource.salvaPedido(pedido);
    }
}
