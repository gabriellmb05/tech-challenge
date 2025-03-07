package br.com.on.fiap.adapter.input.dto.entrada;

import br.com.on.fiap.core.application.dto.entrada.ProdutoQuantidadeEntrada;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoQuantidadeRequest implements ProdutoQuantidadeEntrada {

    @NotNull(message = "O produto do pedido é obrigatório") private Long idProduto;

    @NotNull(message = "A quantidade do produto é obrigatório") private Long quantidade;
}
