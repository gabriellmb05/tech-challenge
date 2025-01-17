package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.relacionamento.RelPedId;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REL_PED_PRO")
public class PedidoProdutoEntidade {

    @EmbeddedId
    private RelPedId id;

    @ManyToOne
    @MapsId("pedId")
    @JoinColumn(name = "PED_ID", referencedColumnName = "id", insertable = false, updatable = false)
    private PedidoEntidade pedId;

    @ManyToOne
    @MapsId("proId")
    @JoinColumn(name = "PRO_ID", referencedColumnName = "id", insertable = false, updatable = false)
    private ProdutoEntidade proId;

    @Column(name = "PED_QT_PEDIDO")
    private Long qtPedido;
}
