package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.entrada.PagamentoRequest;
import br.com.on.fiap.adapter.input.dto.entrada.PedidoQuantidadeRequest;
import br.com.on.fiap.adapter.input.dto.entrada.PedidoRequest;
import java.util.List;

public class DataPoolPedidoSolicitacaoDTO {

    public static PedidoRequest construirPedido() {
        return PedidoRequest.builder()
                .cliente(1L)
                .produtos(List.of(
                        DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(3L, 2L),
                        DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(2L, 2L)))
                .pagamento(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))
                .build();
    }

    public static PedidoRequest construirPedidoComParametros(Long idCliente, List<PedidoQuantidadeRequest> produtos,
                                                             PagamentoRequest pagamento) {
        return PedidoRequest.builder()
                .cliente(idCliente)
                .produtos(produtos)
                .pagamento(pagamento)
                .build();
    }

    public static PedidoRequest construirPedidoSemProdutos() {
        return PedidoRequest.builder()
                .cliente(1L)
                .pagamento(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))
                .build();
    }
}
