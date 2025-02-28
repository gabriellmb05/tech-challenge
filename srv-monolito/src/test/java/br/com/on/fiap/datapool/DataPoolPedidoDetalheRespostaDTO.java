package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.*;
import br.com.on.fiap.hexagono.entities.entidades.SituacaoPedido;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
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
