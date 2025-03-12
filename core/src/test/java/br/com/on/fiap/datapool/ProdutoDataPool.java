package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import br.com.on.fiap.core.domain.Categoria;
import br.com.on.fiap.core.domain.Produto;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.LongStream;
import net.bytebuddy.utility.RandomString;

public class ProdutoDataPool {

    private ProdutoDataPool() {}

    public static Produto criarProdutoExistente(Long id) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(RandomString.make() + id);
        produto.setCategoria(Categoria.LANCHE);
        produto.setPreco(BigDecimal.ONE);
        return produto;
    }

    public static Produto criarProdutoNovo() {
        Produto produto = new Produto();
        produto.setNome(RandomString.make());
        produto.setCategoria(Categoria.LANCHE);
        produto.setPreco(BigDecimal.ONE);
        return produto;
    }

    public static List<Produto> criarProdutosComIdsDinamicos(int quantidade) {
        return LongStream.rangeClosed(1L, quantidade)
                .mapToObj(ProdutoDataPool::criarProdutoExistente)
                .toList();
    }

    public static PaginaResposta<Produto> criarPaginaRespostaProdutosValida() {
        List<Produto> produtos = criarProdutosComIdsDinamicos(2);
        return PaginaResposta.create(produtos, 2L, 1, 10, 1);
    }

    public static PaginaResposta<ProdutoResposta> criarPaginaRespostaProdutosFormatada() {
        List<ProdutoResposta> produtoRespostas = criarProdutosComIdsDinamicos(2).stream()
                .map(ProdutoResposta::create)
                .toList();
        return PaginaResposta.create(produtoRespostas, 2L, 1, 10, 1);
    }
}
