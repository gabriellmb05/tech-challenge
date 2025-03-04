package br.com.on.fiap.adaptadores.pagamento.entrada.dto.resposta;

import br.com.on.fiap.hexagono.entidades.SituacaoPagamento;
import br.com.on.fiap.hexagono.entidades.TipoPagamento;
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
