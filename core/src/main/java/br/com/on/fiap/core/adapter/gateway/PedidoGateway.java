package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoFiltro;
import br.com.on.fiap.core.domain.model.RelPedidoProduto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoGateway {

    Optional<Pedido> atualizarPedido(String protocolo);

    Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page);

    Optional<Pedido> detalhaPedido(String protocolo);

    void salvaPedidoPagamento(Pedido pedido);

    Pedido salvaPedido(Pedido pedido);

    void vincularPedido(List<RelPedidoProduto> pedidoProdutos);
}
