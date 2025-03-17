package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.ProdutoFiltro;

public class ProdutoFiltroDataPool {

    private ProdutoFiltroDataPool() {}

    public static ProdutoFiltro criarFiltroVazio() {
        return criarProdutoFiltro(null, null);
    }

    public static ProdutoFiltro criarProdutoFiltro(String nome, String categoria) {
        return new ProdutoFiltro(nome, categoria);
    }
}
