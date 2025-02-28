package br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaPedidosPortaSaida {

    Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page);
}
