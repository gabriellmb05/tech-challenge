package br.com.on.fiap.adaptadores.pedido.entrada.dto.solicitacao;

import br.com.on.fiap.adaptadores.pagamento.entrada.dto.solicitacao.PagamentoSolicitacaoDTO;
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
public class PedidoSolicitacaoDTO {

    @NotNull(message = "O cliente é obrigatório") private Long cliente;

    @NotNull(message = "Os produtos precisam ser informados") private List<PedidoQuantidadeSolicitacaoDTO> produtos;

    @NotNull(message = "As informações para pagamento precisam ser informadas") private PagamentoSolicitacaoDTO pagamento;
}
