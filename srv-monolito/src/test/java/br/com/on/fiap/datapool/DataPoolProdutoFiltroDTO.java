package br.com.on.fiap.datapool;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroDTO;

public class DataPoolProdutoFiltroDTO {

    public static ProdutoFiltroDTO gerarProdutoXBurguer() {
        return new ProdutoFiltroDTO("x-burguer", "LANCHE");
    }
}
