package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltro filtroVazio() {
        return new ProdutoFiltro();
    }

    public static ProdutoFiltro filtroPorNome(String nome) {
        return new ProdutoFiltro(nome, null);
    }

    public static ProdutoFiltro filtroPorCategoria(Categoria categoria) {
        return new ProdutoFiltro(null, categoria);
    }

    public static ProdutoFiltro filtroPorNomeECategoria(String nome, Categoria categoria) {
        return new ProdutoFiltro(nome, categoria);
    }
}
