package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoSolicitacaoDTO {

	@NotNull(message = "O cliente é obrigatório")
	private Long idCliente;

	@NotNull(message = "O valor do pedido é obrigatório")
	private BigDecimal valor;

	@NotNull(message = "Os produtos precisam ser informados")
	private List<PedidoQuantidadeSolicitacaoDTO> produtos;

}
