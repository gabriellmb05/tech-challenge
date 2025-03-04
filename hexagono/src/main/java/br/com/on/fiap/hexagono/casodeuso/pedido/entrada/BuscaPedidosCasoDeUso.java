package br.com.on.fiap.hexagono.casodeuso.pedido.entrada;

import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaPedidosCasoDeUso {

    Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable);
}
