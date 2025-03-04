package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.SituacaoPedido;
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
    private SituacaoPedido situacao;
    private String protocolo;
    private LocalDateTime dataHora;
}
