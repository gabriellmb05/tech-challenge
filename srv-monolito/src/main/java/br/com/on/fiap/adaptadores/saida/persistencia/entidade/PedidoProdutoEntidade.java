package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.relacionamento.RelPedId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REL_PED_PRO")
public class PedidoProdutoEntidade {

    @EmbeddedId
    private RelPedId id;

    @ManyToOne
    @MapsId("PED_ID")
    @JoinColumn(name = "PED_ID", referencedColumnName = "PED_ID", insertable = false, updatable = false)
    private PedidoEntidade pedId;

    @ManyToOne
    @MapsId("PRO_ID")
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID", insertable = false, updatable = false)
    private ProdutoEntidade proId;

    @Column(name = "PED_QT_PEDIDO")
    private Long qtPedido;
}
