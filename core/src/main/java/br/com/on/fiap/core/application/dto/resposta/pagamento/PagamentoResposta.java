package br.com.on.fiap.core.application.dto.resposta.pagamento;

import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.core.domain.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PagamentoResposta {

    Long getId();

    BigDecimal getVlCompra();

    SituacaoPagamento getStPagamento();

    TipoPagamento getTpPagamento();

    LocalDateTime getDhPagamento();

    static PagamentoResposta create(Pagamento pagamento) {
        return new PagamentoResposta() {
            @Override
            public Long getId() {
                return pagamento.getPagId();
            }

            @Override
            public BigDecimal getVlCompra() {
                return pagamento.getVlCompra();
            }

            @Override
            public SituacaoPagamento getStPagamento() {
                return pagamento.getStPagamento();
            }

            @Override
            public TipoPagamento getTpPagamento() {
                return pagamento.getTpPagamento();
            }

            @Override
            public LocalDateTime getDhPagamento() {
                return pagamento.getDhPagamento();
            }
        };
    }
}
