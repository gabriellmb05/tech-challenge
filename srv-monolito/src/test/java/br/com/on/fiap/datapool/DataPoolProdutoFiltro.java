package br.com.on.fiap.datapool;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.entities.entidades.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    public static ProdutoFiltro gerarProdutoXBurguer() {
        return new ProdutoFiltro("x-burguer", Categoria.LANCHE);
    }
}
