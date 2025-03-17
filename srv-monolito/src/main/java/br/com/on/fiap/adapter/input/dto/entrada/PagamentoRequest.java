package br.com.on.fiap.adapter.input.dto.entrada;

import br.com.on.fiap.core.application.dto.entrada.PagamentoEntrada;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequest implements PagamentoEntrada {

    @NotNull(message = "O tipo do pagamento deve ser informado") private Integer tpPagamento;
}
