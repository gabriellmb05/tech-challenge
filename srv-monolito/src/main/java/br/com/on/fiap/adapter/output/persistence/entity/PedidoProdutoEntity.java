package br.com.on.fiap.adapter.output.persistence.entity;

import br.com.on.fiap.adapter.output.persistence.entity.rel.RelPedId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REL_PED_PRO")
public class PedidoProdutoEntity {

    @EmbeddedId
    private RelPedId id;

    @ManyToOne
    @MapsId("PED_ID")
    @JoinColumn(name = "PED_ID", referencedColumnName = "PED_ID", insertable = false, updatable = false)
    private PedidoEntity pedId;

    @ManyToOne
    @MapsId("PRO_ID")
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID", insertable = false, updatable = false)
    private ProdutoEntity proId;

    @Column(name = "PED_QT_PEDIDO")
    private Long qtPedido;

    public static PedidoProdutoEntity create(
            RelPedId relId, PedidoEntity pedidoEntity, ProdutoEntity produtoEntity, Long qtPedido) {
        return new PedidoProdutoEntity(relId, pedidoEntity, produtoEntity, qtPedido);
    }
}
