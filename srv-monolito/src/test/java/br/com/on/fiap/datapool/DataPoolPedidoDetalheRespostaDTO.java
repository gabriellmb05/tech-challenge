package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.pagamento.entrada.dto.resposta.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.entidades.SituacaoPedido;
import java.util.List;

public class DataPoolPedidoDetalheRespostaDTO {

    private static PedidoDetalhadoRespostaDTO construirPedidoDetalhadoRespostaDTO(
            Long id,
            ClienteRespostaDTO cliente,
            SituacaoPedido situacao,
            List<ProdutoRespostaDTO> produtos,
            PagamentoRespostaDTO pagamento) {
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
