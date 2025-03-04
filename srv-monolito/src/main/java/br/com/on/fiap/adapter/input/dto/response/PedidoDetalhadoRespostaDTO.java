package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.core.application.dto.PagamentoRespostaDTO;
import br.com.on.fiap.core.application.dto.ProdutoRespostaDTO;
import br.com.on.fiap.core.domain.entity.SituacaoPedido;
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
