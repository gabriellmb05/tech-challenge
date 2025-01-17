package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import java.math.BigDecimal;
import java.util.List;

public record PedidoDetalhadoRespostaDTO(
        Long id,
        ClienteRespostaDTO cliente,
        BigDecimal valor,
        SituacaoPedido situacao,
        List<ProdutoRespostaDTO> produtos) {}
