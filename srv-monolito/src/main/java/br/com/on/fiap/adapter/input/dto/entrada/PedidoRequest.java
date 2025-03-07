package br.com.on.fiap.adapter.input.dto.entrada;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.dto.entrada.ProdutoQuantidadeEntrada;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest implements PedidoEntrada {

    @NotNull(message = "O cliente é obrigatório") private Long cliente;

    @NotNull(message = "Os produtos precisam ser informados") private List<PedidoQuantidadeRequest> produtos;

    @NotNull(message = "As informações para pagamento precisam ser informadas") private PagamentoRequest pagamento;

    @Override
    public List<ProdutoQuantidadeEntrada> getProdutos() {
        return List.copyOf(produtos);
    }
}
