package br.com.on.fiap.entidade;

import br.com.on.fiap.entidade.conversor.SituacaoPagamentoConversor;
import br.com.on.fiap.entidade.conversor.TipoPagamentoConversor;
import br.com.on.fiap.hexagono.entidades.SituacaoPagamento;
import br.com.on.fiap.hexagono.entidades.TipoPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
public class PagamentoEntidade {

    private static final String SQ_PAG_PAGAMENTO = "SQ_PAG_PAGAMENTO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PAG_PAGAMENTO)
    @SequenceGenerator(name = SQ_PAG_PAGAMENTO, sequenceName = SQ_PAG_PAGAMENTO, allocationSize = 1)
    @Column(name = "PAG_ID")
    private Long pagId;

    @Column(name = "PAG_VL_COMPRA")
    private BigDecimal vlCompra;

    @Column(name = "PAG_ST_PAGAMENTO")
    @Convert(converter = SituacaoPagamentoConversor.class)
    private SituacaoPagamento stPagamento;

    @Column(name = "PAG_TP_PAGAMENTO")
    @Convert(converter = TipoPagamentoConversor.class)
    private TipoPagamento tpPagamento;

    @Column(name = "PAG_DH_PAGAMENTO")
    private LocalDateTime dhPagamento;
}
