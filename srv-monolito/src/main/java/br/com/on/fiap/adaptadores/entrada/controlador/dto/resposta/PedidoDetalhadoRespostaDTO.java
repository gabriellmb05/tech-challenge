package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.math.BigDecimal;
import java.util.List;

public record PedidoDetalhadoRespostaDTO(Long id, ClienteRespostaDTO cliente, BigDecimal valor, Long situacao,
		List<ProdutoRespostaDTO> produtos) {
}
