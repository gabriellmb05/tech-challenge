package br.com.on.fiap.adapter.output.persistence.entity;

import br.com.on.fiap.adapter.output.persistence.entity.converter.SituacaoPedidoConverter;
import br.com.on.fiap.core.domain.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PED_PEDIDO")
public class PedidoEntity {

    private static final String SQ_PED_PEDIDO = "SQ_PED_PEDIDO";

    @Id
    @Column(name = "PED_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PED_PEDIDO)
    @SequenceGenerator(name = SQ_PED_PEDIDO, sequenceName = SQ_PED_PEDIDO, allocationSize = 1)
    private Long pedId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID", nullable = false)
    private ClienteEntity cliId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PAG_ID", referencedColumnName = "PAG_ID", nullable = false)
    private PagamentoEntity pagId;

    @Column(name = "PED_ST_PEDIDO")
    @Convert(converter = SituacaoPedidoConverter.class)
    private SituacaoPedido stPedido;

    @Column(name = "PED_NM_PROTOCOLO", nullable = false, unique = true)
    private String nmProtocolo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PED_DH_PEDIDO", nullable = false)
    private LocalDateTime dhPedido;

    @ToString.Exclude
    @OneToMany(mappedBy = "pedId", orphanRemoval = true)
    private List<PedidoProdutoEntity> relPedPro = new ArrayList<>();

    public static PedidoEntity create(Pedido pedido, List<PedidoProdutoEntity> pedidoProdutos) {
        ClienteEntity cliente = ClienteEntity.fromDomain(pedido.getCliente());
        PagamentoEntity pagamento = PagamentoEntity.fromDomain(pedido.getPagamento());
        return new PedidoEntity(
                pedido.getId(),
                cliente,
                pagamento,
                pedido.getSituacao(),
                pedido.getProtocolo(),
                pedido.getDataHora(),
                pedidoProdutos);
    }

    public Pedido toDomain(List<PedidoProduto> pedidoProdutos) {
        Cliente cliente = Objects.nonNull(this.getCliId()) ? this.getCliId().toDomain() : null;
        Pagamento pagamento = Objects.nonNull(this.getPagId()) ? this.getPagId().toDomain() : null;
        return new Pedido(
                this.getPedId(),
                pedidoProdutos,
                cliente,
                pagamento,
                this.getStPedido(),
                this.getNmProtocolo(),
                this.getDhPedido());
    }
}
