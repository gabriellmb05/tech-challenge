package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.entrada.PedidoQuantidadeRequest;

public class DataPoolPedidoQuantidadeSolicitacaoDTO {

    public static PedidoQuantidadeRequest construirProduto(Long idProduto, Long quantidade) {
        return PedidoQuantidadeRequest.builder()
                .idProduto(idProduto)
                .quantidade(quantidade)
                .build();
    }
}
