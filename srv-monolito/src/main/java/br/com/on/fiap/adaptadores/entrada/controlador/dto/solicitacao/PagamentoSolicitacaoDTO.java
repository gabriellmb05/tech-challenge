package br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSolicitacaoDTO {

    @NotNull(message = "O valor a ser pago precisa ser informado") private BigDecimal vlCompra;

    @NotNull(message = "O tipo do pagamento deve ser informado") private Integer tpPagamento;
}
