package br.com.on.fiap.adapter.input.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoQuantidadeSolicitacaoDTO {

    @NotNull(message = "O produto do pedido é obrigatório") private Long idProduto;

    @NotNull(message = "A quantidade do produto é obrigatório") private Long quantidade;
}
