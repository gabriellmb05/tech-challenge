package br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoSolicitacaoDTO {

    @NotNull(message = "O cliente é obrigatório") private Long cliente;

    @NotNull(message = "O valor do pedido é obrigatório") private BigDecimal valor;

    @NotNull(message = "Os produtos precisam ser informados") private List<PedidoQuantidadeSolicitacaoDTO> produtos;
}
