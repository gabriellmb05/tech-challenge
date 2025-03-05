package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.domain.model.PagamentoResposta;

public interface PagamentoController {

    PagamentoResposta atualizaPagamento(String nrProtocolo);
}
