package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.conversor.SituacaoPedidoConversor;
import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PED_PEDIDO")
public class PedidoEntidade {

    private static final String SQ_PED_PEDIDO = "SQ_PED_PEDIDO";

    @Id
    @Column(name = "PED_ID")
    @SequenceGenerator(name = SQ_PED_PEDIDO, sequenceName = SQ_PED_PEDIDO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PED_PEDIDO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLI_ID", nullable = false)
    private ClienteEntidade cliId;

    @Column(name = "PED_ST_PEDIDO")
    @Convert(converter = SituacaoPedidoConversor.class)
    private SituacaoPedido stPedido;

    @Column(name = "PED_VL_PEDIDO")
    private BigDecimal vlPedido;

    @OneToMany(mappedBy = "pedId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutoEntidade> relPedPro = new ArrayList<>();

    @Column(name = "PED_NM_PROTOCOLO", nullable = false, unique = true)
    private String nmProtocolo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PED_DH_PEDIDO", nullable = false)
    private LocalDateTime dhPedido;

    @PrePersist
    public void gerarProtocolo() {
        String dataHoraFormatada = this.dhPedido.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Integer numeroAleatorio = new SecureRandom().nextInt(Integer.MAX_VALUE) % 10000;
        this.nmProtocolo = String.format("%s%s%s", dataHoraFormatada, this.id, numeroAleatorio);
    }
}
