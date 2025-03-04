package br.com.on.fiap.adapter.input.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.on.fiap.core.domain.entity.SituacaoPagamento;
import br.com.on.fiap.core.domain.entity.TipoPagamento;
import lombok.Builder;

@Builder
public record PagamentoRespostaDTO(
        Long id,
        BigDecimal vlCompra,
        SituacaoPagamento stPagamento,
        TipoPagamento tpPagamento,
        LocalDateTime dhPagamento) {}
