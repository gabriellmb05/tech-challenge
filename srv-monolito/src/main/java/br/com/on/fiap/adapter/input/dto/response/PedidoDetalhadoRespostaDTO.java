package br.com.on.fiap.adapter.input.dto.response;

import java.util.List;

import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.entity.SituacaoPedido;
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
