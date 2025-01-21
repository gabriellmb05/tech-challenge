package br.com.on.fiap.datapool;

import br.com.on.fiap.hexagono.dominio.*;
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
