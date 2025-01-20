package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoSolicitacaoDTO;
import java.util.List;

public class DataPoolPedidoSolicitacaoDTO {

    public static PedidoSolicitacaoDTO construirPedido() {
        return PedidoSolicitacaoDTO.builder()
                .cliente(1L)
                .produtos(List.of(
                        DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(3L, 2L),
                        DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(2L, 2L)))
                .pagamento(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))
                .build();
    }

    public static PedidoSolicitacaoDTO construirPedidoSemProdutos() {
        return PedidoSolicitacaoDTO.builder()
                .cliente(1L)
                .pagamento(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))
                .build();
    }
}
