package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.model.SituacaoPagamento;
import br.com.on.fiap.core.domain.model.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataPoolPagamentoRespostaDTO {

    private static PagamentoRespostaDTO construirPagamentoRespostaDTO(
            Long id,
            BigDecimal vlCompra,
            SituacaoPagamento stPagamento,
            TipoPagamento tpPagamento,
            LocalDateTime dhPagamento) {
        return new PagamentoRespostaDTO(id, vlCompra, stPagamento, tpPagamento, dhPagamento);
    }

    public static PagamentoRespostaDTO gerarPagamento() {
        return construirPagamentoRespostaDTO(
                1L, BigDecimal.TEN, SituacaoPagamento.PENDENTE, TipoPagamento.PIX, LocalDateTime.now());
    }
}
