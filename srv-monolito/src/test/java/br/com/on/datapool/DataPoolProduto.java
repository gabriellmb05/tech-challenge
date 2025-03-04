package br.com.on.datapool;

import br.com.on.fiap.core.domain.entity.Categoria;
import br.com.on.fiap.core.domain.entity.Produto;
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
