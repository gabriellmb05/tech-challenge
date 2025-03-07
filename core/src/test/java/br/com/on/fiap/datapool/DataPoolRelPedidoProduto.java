package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.PedidoProduto;
import br.com.on.fiap.core.domain.Produto;
import java.util.List;
import java.util.stream.LongStream;

public class DataPoolRelPedidoProduto {

    private DataPoolRelPedidoProduto() {}

    public static PedidoProduto relPedidoProdutoComProduto(Long produtoId) {
        Produto produto = DataPoolProduto.produtoExistente(produtoId);
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(10L);
        return pedidoProduto;
    }

    public static List<PedidoProduto> relPedidoProdutosComProdutos(int quantidade) {
        return LongStream.rangeClosed(1L, quantidade)
                .mapToObj(DataPoolRelPedidoProduto::relPedidoProdutoComProduto)
                .toList();
    }
}
