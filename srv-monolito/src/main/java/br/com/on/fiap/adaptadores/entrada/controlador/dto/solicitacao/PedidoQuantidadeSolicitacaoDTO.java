package br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoQuantidadeSolicitacaoDTO {

    @NotNull(message = "O produto do pedido é obrigatório") private Long idProduto;

    @NotNull(message = "A quantidade do produto é obrigatório") private Long quantidade;
}
