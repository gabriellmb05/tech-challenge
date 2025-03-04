package br.com.on.fiap.core.datapool;

import br.com.on.fiap.core.domain.entity.Categoria;
import br.com.on.fiap.core.domain.entity.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltro filtroVazio() {
        return new ProdutoFiltro();
    }

    public static ProdutoFiltro filtroPorNomeECategoria(String nome, Categoria categoria) {
        return new ProdutoFiltro(nome, categoria);
    }
}
