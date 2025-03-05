package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.response.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adapter.input.dto.response.ProdutoResponse;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.model.PagamentoResposta;
import br.com.on.fiap.core.domain.model.ProdutoResposta;
import br.com.on.fiap.core.domain.model.SituacaoPedido;
import java.util.List;

public class DataPoolPedidoDetalheRespostaDTO {

    private static PedidoDetalhadoRespostaDTO construirPedidoDetalhadoRespostaDTO(
            Long id,
            ClienteRespostaDTO cliente,
            SituacaoPedido situacao,
            List<ProdutoResposta> produtos,
            PagamentoResposta pagamento) {
        return PedidoDetalhadoRespostaDTO.builder()
                .id(id)
                .cliente(cliente)
                .situacao(situacao)
                .produtos(produtos)
                .pagamento(pagamento)
                .build();
    }

    public static PedidoDetalhadoRespostaDTO gerarPedidoDetalhe() {
        return construirPedidoDetalhadoRespostaDTO(
                1L,
                DataPoolClienteRespostaDTO.gerarCliente(),
                SituacaoPedido.REALIZADO,
                DataPoolProdutoRespostaDTO.gerarListaProdutoRespostaDTO(),
                DataPoolPagamentoRespostaDTO.gerarPagamento());
    }
}
