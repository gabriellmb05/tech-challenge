package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.entidades.Categoria;
import br.com.on.fiap.hexagono.entidades.Produto;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.LongStream;
import net.bytebuddy.utility.RandomString;

public class DataPoolProduto {

    private DataPoolProduto() {}

    public static Produto produtoExistente(Long id) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(RandomString.make() + id);
        produto.setCategoria(Categoria.LANCHE);
        produto.setPreco(BigDecimal.ONE);
        return produto;
    }

    public static Produto produtoNovo() {
        Produto produto = new Produto();
        produto.setNome(RandomString.make());
        produto.setCategoria(Categoria.LANCHE);
        produto.setPreco(BigDecimal.ONE);
        return produto;
    }

    public static List<Produto> produtosComIdsDinamicos(int quantidade) {
        return LongStream.rangeClosed(1L, quantidade)
                .mapToObj(DataPoolProduto::produtoExistente)
                .toList();
    }
}
