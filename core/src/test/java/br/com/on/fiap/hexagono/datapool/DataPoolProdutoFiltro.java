package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.domain.entity.Categoria;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltro filtroVazio() {
        return new ProdutoFiltro();
    }

    public static ProdutoFiltro filtroPorNomeECategoria(String nome, Categoria categoria) {
        return new ProdutoFiltro(nome, categoria);
    }
}
