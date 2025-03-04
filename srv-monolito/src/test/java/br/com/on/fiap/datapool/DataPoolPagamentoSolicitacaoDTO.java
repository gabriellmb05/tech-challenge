package br.com.on.fiap.datapool;

import br.com.on.fiap.adapter.input.dto.request.PagamentoSolicitacaoDTO;

public class DataPoolPagamentoSolicitacaoDTO {

    public static PagamentoSolicitacaoDTO construirPagamento(Integer tpPagamento) {
        return PagamentoSolicitacaoDTO.builder().tpPagamento(tpPagamento).build();
    }
}
