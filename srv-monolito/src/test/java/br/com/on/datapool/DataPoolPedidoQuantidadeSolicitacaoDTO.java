package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.request.PedidoQuantidadeEntradaDTO;

public class DataPoolPedidoQuantidadeSolicitacaoDTO {

    public static PedidoQuantidadeEntradaDTO construirProduto(Long idProduto, Long quantidade) {
        return PedidoQuantidadeEntradaDTO.builder()
                .idProduto(idProduto)
                .quantidade(quantidade)
                .build();
    }
}
