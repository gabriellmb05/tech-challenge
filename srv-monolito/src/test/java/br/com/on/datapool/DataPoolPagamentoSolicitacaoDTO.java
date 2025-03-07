package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.request.PagamentoEntradaDTO;

public class DataPoolPagamentoSolicitacaoDTO {

    public static PagamentoEntradaDTO construirPagamento(Integer tpPagamento) {
        return PagamentoEntradaDTO.builder().tpPagamento(tpPagamento).build();
    }
}
