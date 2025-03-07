package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.filtro.ProdutoFiltroRequest;
import br.com.on.fiap.core.domain.Categoria;

public class DataPoolProdutoFiltro {

    public static ProdutoFiltroRequest gerarProdutoXBurguer() {
        return new ProdutoFiltroRequest("x-burguer", Categoria.LANCHE);
    }
}
