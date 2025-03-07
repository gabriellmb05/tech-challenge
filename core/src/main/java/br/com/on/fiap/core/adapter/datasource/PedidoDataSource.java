package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.domain.model.*;
import java.util.Optional;

public interface PedidoDataSource {
    Optional<Pedido> atualizarPedido(String protocolo);

    Pagina<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, Paginacao paginacao);

    Optional<Pedido> detalhaPedido(String protocolo);

    Pedido salvaPedido(Pedido pedido);
}
