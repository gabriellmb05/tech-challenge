package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoId implements Serializable {

	private Long pedidoId;
	private Long produtoId;
}
