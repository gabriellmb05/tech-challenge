package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.PagamentoRespostaDTO;

public interface PagamentoController {

    PagamentoRespostaDTO atualizaPagamento(String nrProtocolo);
}
