package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.entity.Categoria;
import br.com.on.fiap.core.domain.entity.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    public static ProdutoFiltro gerarProdutoXBurguer() {
        return new ProdutoFiltro("x-burguer", Categoria.LANCHE);
    }
}
