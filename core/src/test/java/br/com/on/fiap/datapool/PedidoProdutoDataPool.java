package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.PedidoProduto;
import br.com.on.fiap.core.domain.Produto;
import java.util.List;
import java.util.stream.LongStream;

public class PedidoProdutoDataPool {

    private PedidoProdutoDataPool() {}

    public static PedidoProduto criarPedidoProdutoComProduto(Long produtoId) {
        Produto produto = ProdutoDataPool.criarProdutoExistente(produtoId);
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(10L);
        return pedidoProduto;
    }

    public static List<PedidoProduto> criarPedidoProdutosComProdutos(int quantidade) {
        return LongStream.rangeClosed(1L, quantidade)
                .mapToObj(PedidoProdutoDataPool::criarPedidoProdutoComProduto)
                .toList();
    }
}
