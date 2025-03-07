package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.entrada.PagamentoRequest;

public class DataPoolPagamentoSolicitacaoDTO {

    public static PagamentoRequest construirPagamento(Integer tpPagamento) {
        return PagamentoRequest.builder().tpPagamento(tpPagamento).build();
    }
}
