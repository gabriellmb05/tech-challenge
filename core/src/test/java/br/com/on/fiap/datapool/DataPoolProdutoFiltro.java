package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Categoria;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltro filtroVazio() {
        return new ProdutoFiltro();
    }

    public static ProdutoFiltro filtroPorNomeECategoria(String nome, Categoria categoria) {
        return new ProdutoFiltro(nome, categoria);
    }
}
