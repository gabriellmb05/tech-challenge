package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.entrada.PedidoSolicitacao;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import br.com.on.fiap.core.application.dto.resposta.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoResposta;

public interface PedidoController {

    PedidoResposta inserePedido(PedidoSolicitacao pedidoSolicitacao);

    Pagina<PedidoResposta> listarPedidoComFiltro(PedidoFiltroEntrada filtro, Paginacao paginacao);

    PedidoDetalhadoResposta detalhaPedido(String protocolo);

    PedidoResposta atualizarPedido(String protocolo);
}
