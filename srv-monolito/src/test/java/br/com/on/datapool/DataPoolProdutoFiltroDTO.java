package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroEntradaRequest;
import br.com.on.fiap.core.domain.Categoria;

public class DataPoolProdutoFiltroDTO {

    public static ProdutoFiltroEntradaRequest gerarProdutoXBurguer() {
        return new ProdutoFiltroEntradaRequest("LANCHE", Categoria.ACOMPANHAMENTO);
    }
}
