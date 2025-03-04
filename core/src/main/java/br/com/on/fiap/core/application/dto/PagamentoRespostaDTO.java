package br.com.on.fiap.core.application.dto;

import br.com.on.fiap.core.domain.entity.SituacaoPagamento;
import br.com.on.fiap.core.domain.entity.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoRespostaDTO(
        Long id,
        BigDecimal vlCompra,
        SituacaoPagamento stPagamento,
        TipoPagamento tpPagamento,
        LocalDateTime dhPagamento) {}
