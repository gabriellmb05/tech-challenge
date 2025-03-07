package br.com.on.fiap.adapter.input.dto.request;

import br.com.on.fiap.core.application.dto.entrada.pagamento.PagamentoEntrada;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntradaDTO implements PagamentoEntrada {

    @NotNull(message = "O tipo do pagamento deve ser informado") private Integer tpPagamento;
}
