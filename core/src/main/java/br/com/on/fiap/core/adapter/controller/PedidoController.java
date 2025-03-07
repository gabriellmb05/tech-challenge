package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.domain.model.*;

public interface PedidoController {

    PedidoResposta inserePedido(PedidoSolicitacao pedidoSolicitacao);

    Pagina<PedidoResposta> listarPedidoComFiltro(PedidoFiltroEntrada filtro, Paginacao paginacao);

    PedidoDetalhadoResposta detalhaPedido(String protocolo);

    PedidoResposta atualizarPedido(String protocolo);
}
