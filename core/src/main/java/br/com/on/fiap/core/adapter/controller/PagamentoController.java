package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.resposta.PagamentoResposta;

public interface PagamentoController {

    PagamentoResposta atualizaPagamento(String nrProtocolo);
}
