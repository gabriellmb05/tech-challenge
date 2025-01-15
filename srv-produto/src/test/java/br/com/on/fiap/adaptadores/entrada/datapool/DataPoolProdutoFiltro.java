package br.com.on.fiap.adaptadores.entrada.datapool;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;

public class DataPoolProdutoFiltro {

    public static ProdutoFiltro gerarProdutoXBurguer() {
        return new ProdutoFiltro("x-burguer",  Categoria.LANCHE);
    }

}
