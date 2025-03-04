package br.com.on.fiap.hexagono.application.usecase.pedido.base;

import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.entity.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoBuscaUseCase {

    Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable);
}
