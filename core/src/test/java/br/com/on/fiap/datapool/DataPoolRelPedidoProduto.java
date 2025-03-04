package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.RelPedidoProduto;
import java.util.List;
import java.util.stream.LongStream;

public class DataPoolRelPedidoProduto {

    private DataPoolRelPedidoProduto() {}

    public static RelPedidoProduto relPedidoProdutoComProduto(Long produtoId) {
        Produto produto = DataPoolProduto.produtoExistente(produtoId);
        RelPedidoProduto relPedidoProduto = new RelPedidoProduto();
        relPedidoProduto.setProduto(produto);
        relPedidoProduto.setQuantidade(10L);
        return relPedidoProduto;
    }

    public static List<RelPedidoProduto> relPedidoProdutosComProdutos(int quantidade) {
        return LongStream.rangeClosed(1L, quantidade)
                .mapToObj(DataPoolRelPedidoProduto::relPedidoProdutoComProduto)
                .toList();
    }
}
