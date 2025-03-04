package br.com.on.fiap.entidade;

import br.com.on.fiap.entidade.conversor.SituacaoPedidoConversor;
import br.com.on.fiap.hexagono.domain.entity.SituacaoPedido;
import jakarta.persistence.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PED_PEDIDO")
public class PedidoEntidade {

    private static final String SQ_PED_PEDIDO = "SQ_PED_PEDIDO";

    @Id
    @Column(name = "PED_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PED_PEDIDO)
    @SequenceGenerator(name = SQ_PED_PEDIDO, sequenceName = SQ_PED_PEDIDO, allocationSize = 1)
    private Long pedId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID", nullable = false)
    private ClienteEntidade cliId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAG_ID", referencedColumnName = "PAG_ID", nullable = false)
    private PagamentoEntidade pagId;

    @Column(name = "PED_ST_PEDIDO")
    @Convert(converter = SituacaoPedidoConversor.class)
    private SituacaoPedido stPedido;

    @Column(name = "PED_NM_PROTOCOLO", nullable = false, unique = true)
    private String nmProtocolo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PED_DH_PEDIDO", nullable = false)
    private LocalDateTime dhPedido;

    @ToString.Exclude
    @OneToMany(mappedBy = "pedId", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<PedidoProdutoEntidade> relPedPro = new ArrayList<>();

    @PrePersist
    public void gerarProtocolo() {
        String dataHoraFormatada = this.dhPedido.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Integer numeroAleatorio = new SecureRandom().nextInt(Integer.MAX_VALUE) % 10000;
        this.nmProtocolo = String.format("%s%s%s", dataHoraFormatada, this.pedId, numeroAleatorio);
    }
}
