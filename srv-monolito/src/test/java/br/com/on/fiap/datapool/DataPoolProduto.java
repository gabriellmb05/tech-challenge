package br.com.on.fiap.datapool;

import br.com.on.fiap.hexagono.entidades.Categoria;
import br.com.on.fiap.hexagono.entidades.Produto;
import java.math.BigDecimal;
import java.util.List;

public class DataPoolProduto {

    public static Produto gerarProdutoXBurguer() {
        return new Produto(1L, "x-burguer", Categoria.LANCHE, BigDecimal.TEN);
    }

    public static List<Produto> gerarListaProdutos() {
        return List.of(gerarProdutoXBurguer(), new Produto(2L, "pizza", Categoria.LANCHE, BigDecimal.valueOf(20)));
    }
}
