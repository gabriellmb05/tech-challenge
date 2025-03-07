package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroEntradaRequest;
import br.com.on.fiap.core.domain.Categoria;

public class DataPoolProdutoFiltro {

    public static ProdutoFiltroEntradaRequest gerarProdutoXBurguer() {
        return new ProdutoFiltroEntradaRequest("x-burguer", Categoria.LANCHE);
    }
}
