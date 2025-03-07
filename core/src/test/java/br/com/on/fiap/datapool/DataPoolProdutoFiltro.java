package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltro filtroVazio() {
        return criarProdutoFiltro(null, null);
    }

    public static ProdutoFiltro criarProdutoFiltro(String nome, String categoria) {
        return new ProdutoFiltro(nome, categoria);
    }
}
