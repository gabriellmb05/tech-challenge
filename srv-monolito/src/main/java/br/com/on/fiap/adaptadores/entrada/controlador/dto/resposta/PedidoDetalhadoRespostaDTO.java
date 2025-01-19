package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalhadoRespostaDTO {

    private Long id;
    private ClienteRespostaDTO cliente;
    private BigDecimal valor;
    private SituacaoPedido situacao;
    private List<ProdutoRespostaDTO> produtos;
    private PagamentoRespostaDTO pagamento;
}
