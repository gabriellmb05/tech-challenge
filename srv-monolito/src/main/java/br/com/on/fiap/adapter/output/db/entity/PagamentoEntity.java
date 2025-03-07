package br.com.on.fiap.adapter.output.db.entity;

import br.com.on.fiap.adapter.output.db.entity.converter.SituacaoPagamentoConverter;
import br.com.on.fiap.adapter.output.db.entity.converter.TipoPagamentoConverter;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.core.domain.TipoPagamento;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PAG_PAGAMENTO")
public class PagamentoEntity {

    private static final String SQ_PAG_PAGAMENTO = "SQ_PAG_PAGAMENTO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PAG_PAGAMENTO)
    @SequenceGenerator(name = SQ_PAG_PAGAMENTO, sequenceName = SQ_PAG_PAGAMENTO, allocationSize = 1)
    @Column(name = "PAG_ID")
    private Long pagId;

    @Column(name = "PAG_VL_COMPRA")
    private BigDecimal vlCompra;

    @Column(name = "PAG_ST_PAGAMENTO")
    @Convert(converter = SituacaoPagamentoConverter.class)
    private SituacaoPagamento stPagamento;

    @Column(name = "PAG_TP_PAGAMENTO")
    @Convert(converter = TipoPagamentoConverter.class)
    private TipoPagamento tpPagamento;

    @Column(name = "PAG_DH_PAGAMENTO")
    private LocalDateTime dhPagamento;

    public static PagamentoEntity fromDomain(Pagamento pagamento) {
        return new PagamentoEntity(
                pagamento.getPagId(),
                pagamento.getVlCompra(),
                pagamento.getStPagamento(),
                pagamento.getTpPagamento(),
                pagamento.getDhPagamento());
    }

    public Pagamento toDomain() {
        return new Pagamento(pagId, stPagamento, vlCompra, tpPagamento, dhPagamento);
    }
}
