package br.com.on.fiap.adapter.input.dto.request;

import br.com.on.fiap.core.application.dto.entrada.PagamentoSolicitacao;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSolicitacaoDTO implements PagamentoSolicitacao {

    @NotNull(message = "O tipo do pagamento deve ser informado") private Integer tpPagamento;
}
