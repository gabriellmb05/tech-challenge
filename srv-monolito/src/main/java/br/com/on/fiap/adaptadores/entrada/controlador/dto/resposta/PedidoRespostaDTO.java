package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PedidoRespostaDTO(
        Long id, BigDecimal valor, SituacaoPedido situacao, String protocolo, LocalDateTime dataHora) {}
