package br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta;

import br.com.on.fiap.hexagono.entities.entidades.SituacaoPedido;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
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
    private SituacaoPedido situacao;
    private List<ProdutoRespostaDTO> produtos;
    private PagamentoRespostaDTO pagamento;
}
