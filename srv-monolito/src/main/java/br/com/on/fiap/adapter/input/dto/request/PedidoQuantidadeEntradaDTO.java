package br.com.on.fiap.adapter.input.dto.request;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoQuantidadeEntrada;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoQuantidadeEntradaDTO implements ProdutoQuantidadeEntrada {

    @NotNull(message = "O produto do pedido é obrigatório") private Long idProduto;

    @NotNull(message = "A quantidade do produto é obrigatório") private Long quantidade;
}
