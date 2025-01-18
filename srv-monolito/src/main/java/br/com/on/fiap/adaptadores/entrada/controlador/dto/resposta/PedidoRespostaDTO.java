package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRespostaDTO {

    private Long id;
    private BigDecimal valor;
    private SituacaoPedido situacao;
    private String protocolo;
    private LocalDateTime dataHora;
}
