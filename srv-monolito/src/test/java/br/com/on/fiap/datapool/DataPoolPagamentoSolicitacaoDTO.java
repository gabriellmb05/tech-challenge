package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PagamentoSolicitacaoDTO;
import java.math.BigDecimal;

public class DataPoolPagamentoSolicitacaoDTO {

    public static PagamentoSolicitacaoDTO construirPagamento(BigDecimal vlCompra, Integer tpPagamento) {
        return PagamentoSolicitacaoDTO.builder()
                .vlCompra(vlCompra)
                .tpPagamento(tpPagamento)
                .build();
    }
}
