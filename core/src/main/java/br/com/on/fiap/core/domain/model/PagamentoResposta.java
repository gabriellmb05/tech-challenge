package br.com.on.fiap.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PagamentoResposta {

    Long getId();

    BigDecimal getVlCompra();

    SituacaoPagamento getStPagamento();

    TipoPagamento getTpPagamento();

    LocalDateTime getDhPagamento();

    static PagamentoResposta create(
            Long id,
            BigDecimal vlCompra,
            SituacaoPagamento stPagamento,
            TipoPagamento tpPagamento,
            LocalDateTime dhPagamento) {
        return new PagamentoResposta() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public BigDecimal getVlCompra() {
                return vlCompra;
            }

            @Override
            public SituacaoPagamento getStPagamento() {
                return stPagamento;
            }

            @Override
            public TipoPagamento getTpPagamento() {
                return tpPagamento;
            }

            @Override
            public LocalDateTime getDhPagamento() {
                return dhPagamento;
            }
        };
    }
}
