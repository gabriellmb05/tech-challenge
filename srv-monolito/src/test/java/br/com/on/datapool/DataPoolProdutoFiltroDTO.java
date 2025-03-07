package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroRequest;
import br.com.on.fiap.core.domain.Categoria;

public class DataPoolProdutoFiltroDTO {

    public static ProdutoFiltroRequest gerarProdutoXBurguer() {
        return new ProdutoFiltroRequest("LANCHE", Categoria.ACOMPANHAMENTO);
    }
}
