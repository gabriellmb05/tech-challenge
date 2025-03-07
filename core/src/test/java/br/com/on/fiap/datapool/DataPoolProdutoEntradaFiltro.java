package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;

public class DataPoolProdutoEntradaFiltro {

    private DataPoolProdutoEntradaFiltro() {}

    public static ProdutoFiltroEntrada filtroVazio() {
        return criarProdutoFiltro(null, null);
    }

    public static ProdutoFiltroEntrada criarProdutoFiltro(String nome, String categoria) {
        return ProdutoFiltroEntrada.create(nome, categoria);
    }
}
