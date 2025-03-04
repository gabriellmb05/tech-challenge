package br.com.on.fiap.hexagono.adaptadores.datasource;

import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import br.com.on.fiap.hexagono.entidades.RelPedidoProduto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoDataSource {
    Optional<Pedido> atualizarPedido(String protocolo);

    Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page);

    Optional<Pedido> detalhaPedido(String protocolo);

    void salvaPedidoPagamento(Pedido pedido);

    Pedido salvaPedido(Pedido pedido);

    void vincularPedido(List<RelPedidoProduto> pedidoProdutos);
}
