package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.request.PedidoEntradaDTO;
import java.util.List;

public class DataPoolPedidoSolicitacaoDTO {

    public static PedidoEntradaDTO construirPedido() {
        return PedidoEntradaDTO.builder()
                .cliente(1L)
                .produtos(List.of(
                        DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(3L, 2L),
                        DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(2L, 2L)))
                .pagamento(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))
                .build();
    }

    public static PedidoEntradaDTO construirPedidoSemProdutos() {
        return PedidoEntradaDTO.builder()
                .cliente(1L)
                .pagamento(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))
                .build();
    }
}
