package br.com.on.fiap.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoRespostaDTO(
        Long id,
        BigDecimal vlCompra,
        SituacaoPagamento stPagamento,
        TipoPagamento tpPagamento,
        LocalDateTime dhPagamento) {}
