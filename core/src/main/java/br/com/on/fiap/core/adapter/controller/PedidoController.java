package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoResposta;

public interface PedidoController {

    PedidoResposta inserePedido(PedidoEntrada pedidoEntrada);

    PaginaResposta<PedidoResposta> listarPedidoComFiltro(
            PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    PaginaResposta<PedidoResposta> listarPedidoComFiltro(PaginacaoResposta paginacaoResposta);

    PedidoDetalhadoResposta detalhaPedido(String protocolo);

    PedidoResposta atualizarPedido(String protocolo);
}
