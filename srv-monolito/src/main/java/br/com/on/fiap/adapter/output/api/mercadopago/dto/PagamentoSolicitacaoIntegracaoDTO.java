package br.com.on.fiap.adapter.output.api.mercadopago.dto;

import br.com.on.fiap.core.domain.entity.Pagamento;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSolicitacaoIntegracaoDTO {

    private BigDecimal amount;
    private Payment payment;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Payment {
        private String type;
    }

    public static PagamentoSolicitacaoIntegracaoDTO criarPagamentoIntegracao(Pagamento pagamento) {
        return PagamentoSolicitacaoIntegracaoDTO.builder()
                .amount(pagamento.getVlCompra())
                .payment(new Payment(pagamento.getTpPagamento().getDescricao()))
                .build();
    }
}
