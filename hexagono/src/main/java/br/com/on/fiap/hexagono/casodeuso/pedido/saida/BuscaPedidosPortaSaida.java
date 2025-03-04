package br.com.on.fiap.hexagono.casodeuso.pedido.saida;

import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaPedidosPortaSaida {

    Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page);
}
