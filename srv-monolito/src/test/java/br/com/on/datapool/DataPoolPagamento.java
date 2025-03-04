package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.SituacaoPagamento;
import br.com.on.fiap.core.domain.model.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataPoolPagamento {

    public static Pagamento gerarPagamento() {
        return new Pagamento(1L, SituacaoPagamento.PENDENTE, BigDecimal.TEN, TipoPagamento.PIX, LocalDateTime.now());
    }

    public static Pagamento gerarPagamento2() {
        return new Pagamento(2L, SituacaoPagamento.PENDENTE, BigDecimal.TEN, TipoPagamento.PIX, LocalDateTime.now());
    }
}
