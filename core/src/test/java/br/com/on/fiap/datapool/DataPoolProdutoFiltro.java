package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Categoria;
import br.com.on.fiap.core.domain.model.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltro filtroVazio() {
        return criarProdutoFiltro(null, null);
    }

    public static ProdutoFiltro criarProdutoFiltro(String nome, Categoria categoria) {
        return ProdutoFiltro.create(nome, categoria);
    }
}
