package br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaPedidosCasoDeUso {

    Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable);
}
