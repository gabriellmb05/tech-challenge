package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.core.domain.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDataPool {

    private PagamentoDataPool() {}

    public static Pagamento criarPagamentoExistente(Long id) {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(id);
        pagamento.setTpPagamento(TipoPagamento.PIX);
        pagamento.setVlCompra(BigDecimal.valueOf(100));
        pagamento.setStPagamento(SituacaoPagamento.PENDENTE);
        return pagamento;
    }

    public static Pagamento criarPagamentoComTipoESituacao(
            Long id, TipoPagamento tipoPagamento, SituacaoPagamento situacaoPagamento) {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(id);
        pagamento.setVlCompra(BigDecimal.valueOf(200));
        pagamento.setTpPagamento(tipoPagamento);
        pagamento.setStPagamento(situacaoPagamento);
        return pagamento;
    }

    public static Pagamento criarPagamentoValido() {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(1L);
        pagamento.setStPagamento(SituacaoPagamento.APROVADO);
        pagamento.setVlCompra(BigDecimal.valueOf(200));
        pagamento.setTpPagamento(TipoPagamento.CREDITO);
        pagamento.setDhPagamento(LocalDateTime.now());
        return pagamento;
    }

    public static Pagamento criarPagamentoValidoSemData() {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(1L);
        pagamento.setStPagamento(SituacaoPagamento.APROVADO);
        pagamento.setVlCompra(BigDecimal.valueOf(200));
        pagamento.setTpPagamento(TipoPagamento.CREDITO);
        pagamento.setDhPagamento(null);
        return pagamento;
    }

    public static Pagamento criarPagamentoPendente() {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(3L);
        pagamento.setStPagamento(SituacaoPagamento.PENDENTE);
        pagamento.setVlCompra(BigDecimal.valueOf(50));
        pagamento.setTpPagamento(TipoPagamento.DEBITO);
        pagamento.setDhPagamento(null);
        return pagamento;
    }

    public static Pagamento criarPagamentoParaAtualizacao() {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(1L);
        pagamento.setStPagamento(SituacaoPagamento.PENDENTE);
        pagamento.setVlCompra(BigDecimal.valueOf(500));
        pagamento.setTpPagamento(TipoPagamento.CREDITO);
        pagamento.setDhPagamento(null);
        return pagamento;
    }

    public static Pagamento criarPagamentoFinalizado() {
        Pagamento pagamento = new Pagamento();
        pagamento.setPagId(2L);
        pagamento.setStPagamento(SituacaoPagamento.APROVADO);
        pagamento.setVlCompra(BigDecimal.valueOf(300));
        pagamento.setTpPagamento(TipoPagamento.PIX);
        pagamento.setDhPagamento(LocalDateTime.now());
        return pagamento;
    }
}
