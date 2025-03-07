package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.pedido.PedidoEntrada;
import br.com.on.fiap.core.application.dto.filtro.pedido.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.pedido.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.pedido.PedidoResposta;

public interface PedidoController {

    PedidoResposta inserePedido(PedidoEntrada pedidoEntrada);

    PaginaResposta<PedidoResposta> listarPedidoComFiltro(
            PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    PedidoDetalhadoResposta detalhaPedido(String protocolo);

    PedidoResposta atualizarPedido(String protocolo);
}
