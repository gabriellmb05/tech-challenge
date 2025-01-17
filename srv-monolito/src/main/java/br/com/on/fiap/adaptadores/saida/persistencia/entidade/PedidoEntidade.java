package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

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
@Table(name = "pedidos")
public class PedidoEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidos_id_seq")
	@SequenceGenerator(name = "pedidos_id_seq", sequenceName = "pedidos_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteEntidade cliente;

	private Long situacao;

	private BigDecimal valor;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoProdutoEntidade> produtos = new ArrayList<>();

	@Column(nullable = false, unique = true)
	private String protocolo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataHora;

	@PrePersist
	public void gerarProtocolo() {
		String dataHoraFormatada = this.dataHora.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		Integer numeroAleatorio = new SecureRandom().nextInt(Integer.MAX_VALUE) % 10000;
		this.protocolo = String.format("%s%s%s", dataHoraFormatada, this.id, numeroAleatorio);
	}
}
