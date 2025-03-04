package br.com.on.fiap.core.usecase.pedido;

import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.entity.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoListaUseCase {

    Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable);
}
