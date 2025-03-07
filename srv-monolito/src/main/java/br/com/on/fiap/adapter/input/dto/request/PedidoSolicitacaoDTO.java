package br.com.on.fiap.adapter.input.dto.request;

import br.com.on.fiap.core.domain.model.PedidoSolicitacao;
import br.com.on.fiap.core.domain.model.ProdutoQuantidadeSolicitacao;
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
public class PedidoSolicitacaoDTO implements PedidoSolicitacao {

    @NotNull(message = "O cliente é obrigatório") private Long cliente;

    @NotNull(message = "Os produtos precisam ser informados") private List<PedidoQuantidadeSolicitacaoDTO> produtos;

    @NotNull(message = "As informações para pagamento precisam ser informadas") private PagamentoSolicitacaoDTO pagamento;

    @Override
    public List<ProdutoQuantidadeSolicitacao> getProdutos() {
        return List.copyOf(produtos);
    }
}
