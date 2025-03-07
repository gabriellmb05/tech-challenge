package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.domain.Categoria;

public class DataPoolProdutoFiltro {

    private DataPoolProdutoFiltro() {}

    public static ProdutoFiltroEntrada filtroVazio() {
        return criarProdutoFiltro(null, null);
    }

    public static ProdutoFiltroEntrada criarProdutoFiltro(String nome, Categoria categoria) {
        return ProdutoFiltroEntrada.create(nome, categoria);
    }
}
