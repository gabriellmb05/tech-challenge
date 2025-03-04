package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.pedido.entrada.dto.solicitacao.PedidoQuantidadeSolicitacaoDTO;

public class DataPoolPedidoQuantidadeSolicitacaoDTO {

    public static PedidoQuantidadeSolicitacaoDTO construirProduto(Long idProduto, Long quantidade) {
        return PedidoQuantidadeSolicitacaoDTO.builder()
                .idProduto(idProduto)
                .quantidade(quantidade)
                .build();
    }
}
