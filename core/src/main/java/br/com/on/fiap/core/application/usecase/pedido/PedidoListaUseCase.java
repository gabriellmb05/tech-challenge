package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.domain.model.*;

public interface PedidoListaUseCase {

    Pagina<Pedido> buscarPedidosComFiltro(PedidoFiltroEntrada filtro, Paginacao paginacao);
}
