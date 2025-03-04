package br.com.on.fiap.datapool;

import br.com.on.fiap.adapter.input.dto.response.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.entity.SituacaoPagamento;
import br.com.on.fiap.core.domain.entity.TipoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataPoolPagamentoRespostaDTO {

    private static PagamentoRespostaDTO construirPagamentoRespostaDTO(
            Long id,
            BigDecimal vlCompra,
            SituacaoPagamento stPagamento,
            TipoPagamento tpPagamento,
            LocalDateTime dhPagamento) {
        return PagamentoRespostaDTO.builder()
                .id(id)
                .vlCompra(vlCompra)
                .stPagamento(stPagamento)
                .tpPagamento(tpPagamento)
                .dhPagamento(dhPagamento)
                .build();
    }

    public static PagamentoRespostaDTO gerarPagamento() {
        return construirPagamentoRespostaDTO(
                1L, BigDecimal.TEN, SituacaoPagamento.PENDENTE, TipoPagamento.PIX, LocalDateTime.now());
    }
}
