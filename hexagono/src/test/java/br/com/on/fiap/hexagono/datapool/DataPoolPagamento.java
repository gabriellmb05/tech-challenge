package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.SituacaoPagamento;
import br.com.on.fiap.hexagono.dominio.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataPoolPagamento {

    private DataPoolPagamento() {}

    public static Pagamento pagamentoExistente(Long id) {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(id);
        pagamento.setTpPagamento(TipoPagamento.PIX);
        pagamento.setVlCompra(BigDecimal.valueOf(100.00));
        pagamento.setStPagamento(SituacaoPagamento.PENDENTE);
        return pagamento;
    }

    public static Pagamento pagamentoComTipoESituacao(
            Long id, TipoPagamento tipoPagamento, SituacaoPagamento situacaoPagamento) {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(id);
        pagamento.setVlCompra(BigDecimal.valueOf(200.00));
        pagamento.setTpPagamento(tipoPagamento);
        pagamento.setStPagamento(situacaoPagamento);
        return pagamento;
    }

    public static Pagamento pagamentoValido() {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(1L);
        pagamento.setStPagamento(SituacaoPagamento.APROVADO);
        pagamento.setVlCompra(new BigDecimal("200.00"));
        pagamento.setTpPagamento(TipoPagamento.CREDITO);
        pagamento.setDhPagamento(LocalDateTime.now());
        return pagamento;
    }
}
