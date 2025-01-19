package br.com.on.fiap.hexagono.portas.entrada.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaPedidosPortaEntrada {

    Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable);
}
