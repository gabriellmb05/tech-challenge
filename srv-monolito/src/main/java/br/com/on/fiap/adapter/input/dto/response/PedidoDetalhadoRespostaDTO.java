package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.model.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.model.ProdutoRespostaDTO;
import br.com.on.fiap.core.domain.model.SituacaoPedido;
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
