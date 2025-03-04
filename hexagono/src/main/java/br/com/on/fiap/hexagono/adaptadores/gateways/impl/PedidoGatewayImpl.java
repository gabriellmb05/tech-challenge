package br.com.on.fiap.hexagono.adaptadores.gateways.impl;

import br.com.on.fiap.hexagono.adaptadores.gateways.PedidoGateway;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import br.com.on.fiap.hexagono.entidades.RelPedidoProduto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PedidoGatewayImpl implements PedidoGateway {

    @Override
    public Optional<Pedido> atualizarPedido(String protocolo) {
        return Optional.empty();
    }

    @Override
    public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
        return null;
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        return Optional.empty();
    }

    @Override
    public void salvaPedidoPagamento(Pedido pedido) {}

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        return null;
    }

    @Override
    public void vincularPedido(List<RelPedidoProduto> pedidoProdutos) {}
}
