package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Categoria;
import br.com.on.fiap.core.domain.model.ProdutoEntrada;
import java.math.BigDecimal;
import net.bytebuddy.utility.RandomString;

public class DataPoolProdutoEntrada {

    private DataPoolProdutoEntrada() {}

    public static ProdutoEntrada produtoExistente(Long id) {
        return criarProdutoEntrada((RandomString.make() + id), Categoria.LANCHE.name(), BigDecimal.ONE);
    }

    public static ProdutoEntrada criarProdutoEntrada(String nome, String categoria, BigDecimal preco) {
        return ProdutoEntrada.create(nome, categoria, preco);
    }
}
