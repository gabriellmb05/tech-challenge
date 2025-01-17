package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PedidoRespostaDTO(Long id, BigDecimal valor, Long situacao) {
}
