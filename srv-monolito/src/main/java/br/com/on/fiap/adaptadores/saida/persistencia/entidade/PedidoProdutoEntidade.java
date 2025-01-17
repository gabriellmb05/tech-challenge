package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido_produto")
public class PedidoProdutoEntidade {

    @EmbeddedId
    private PedidoProdutoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PedidoEntidade pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProdutoEntidade produto;

    private Long quantidade;
}
