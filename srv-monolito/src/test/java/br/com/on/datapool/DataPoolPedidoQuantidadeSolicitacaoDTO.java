package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.request.PedidoQuantidadeSolicitacaoDTO;

public class DataPoolPedidoQuantidadeSolicitacaoDTO {

    public static PedidoQuantidadeSolicitacaoDTO construirProduto(Long idProduto, Long quantidade) {
        return PedidoQuantidadeSolicitacaoDTO.builder()
                .idProduto(idProduto)
                .quantidade(quantidade)
                .build();
    }
}
