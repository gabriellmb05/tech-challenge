package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import br.com.on.fiap.hexagono.dominio.SituacaoPagamento;
import br.com.on.fiap.hexagono.dominio.TipoPagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PagamentoRespostaDTO(
        Long id,
        BigDecimal vlCompra,
        SituacaoPagamento stPagamento,
        TipoPagamento tpPagamento,
        LocalDateTime dhPagamento) {}
