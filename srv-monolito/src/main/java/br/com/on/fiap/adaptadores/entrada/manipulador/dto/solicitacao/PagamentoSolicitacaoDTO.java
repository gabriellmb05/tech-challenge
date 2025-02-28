package br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSolicitacaoDTO {

    @NotNull(message = "O tipo do pagamento deve ser informado") private Integer tpPagamento;
}
