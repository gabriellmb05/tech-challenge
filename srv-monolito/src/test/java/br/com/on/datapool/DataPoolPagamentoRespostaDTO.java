package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.PagamentoResposta;
import br.com.on.fiap.core.domain.model.SituacaoPagamento;
import br.com.on.fiap.core.domain.model.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataPoolPagamentoRespostaDTO {

    private static PagamentoResposta construirPagamentoRespostaDTO(
            Long id,
            BigDecimal vlCompra,
            SituacaoPagamento stPagamento,
            TipoPagamento tpPagamento,
            LocalDateTime dhPagamento) {
        return PagamentoResposta.create(id, vlCompra, stPagamento, tpPagamento, dhPagamento);
    }

    public static PagamentoResposta gerarPagamento() {
        return construirPagamentoRespostaDTO(
                1L, BigDecimal.TEN, SituacaoPagamento.PENDENTE, TipoPagamento.PIX, LocalDateTime.now());
    }
}
