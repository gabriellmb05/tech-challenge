package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class PedidoEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidos_id_seq")
	@SequenceGenerator(name = "pedidos_id_seq", sequenceName = "pedidos_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteEntidade clienteEntidade;

	@Enumerated(EnumType.STRING)
	private SituacaoPedido situacao;

	private BigDecimal valor;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RelPedidoProdutoEntidade> produtos;
}
