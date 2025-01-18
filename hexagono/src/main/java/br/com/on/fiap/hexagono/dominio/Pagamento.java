package br.com.on.fiap.hexagono.dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {

    private Long pagId;
    private SituacaoPagamento stPagamento;
    private BigDecimal vlCompra;
    private TipoPagamento tpPagamento;
    private LocalDateTime dhPagamento;

    public Pagamento() {
    }

    public Pagamento(Long pagId, SituacaoPagamento stPagamento, BigDecimal vlCompra, TipoPagamento tpPagamento, LocalDateTime dhPagamento) {
        this.pagId = pagId;
        this.stPagamento = stPagamento;
        this.vlCompra = vlCompra;
        this.tpPagamento = tpPagamento;
        this.dhPagamento = dhPagamento;
    }

    public Long getPagId() {
        return pagId;
    }

    public void setPagId(Long pagId) {
        this.pagId = pagId;
    }

    public SituacaoPagamento getStPagamento() {
        return stPagamento;
    }

    public void setStPagamento(SituacaoPagamento stPagamento) {
        this.stPagamento = stPagamento;
    }

    public BigDecimal getVlCompra() {
        return vlCompra;
    }

    public void setVlCompra(BigDecimal vlCompra) {
        this.vlCompra = vlCompra;
    }

    public TipoPagamento getTpPagamento() {
        return tpPagamento;
    }

    public void setTpPagamento(TipoPagamento tpPagamento) {
        this.tpPagamento = tpPagamento;
    }

    public LocalDateTime getDhPagamento() {
        return dhPagamento;
    }

    public void setDhPagamento(LocalDateTime dhPagamento) {
        this.dhPagamento = dhPagamento;
    }
}
