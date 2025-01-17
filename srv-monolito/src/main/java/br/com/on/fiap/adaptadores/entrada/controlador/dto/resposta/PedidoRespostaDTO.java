package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PedidoRespostaDTO(Long id, BigDecimal valor, Long situacao, String protocolo, LocalDateTime dataHora) {
}
