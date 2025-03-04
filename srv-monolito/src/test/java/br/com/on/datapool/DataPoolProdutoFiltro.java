package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.Categoria;

public class DataPoolProdutoFiltro {

    public static ProdutoFiltro gerarProdutoXBurguer() {
        return new ProdutoFiltro("x-burguer", Categoria.LANCHE);
    }
}
