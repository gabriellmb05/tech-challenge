package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.RelPedidoProduto;
import java.util.List;

public class DataPoolRelPedidoProduto {

    public static List<RelPedidoProduto> gerarListaRelPedidoProduto(Pedido pedido) {
        return List.of(
                new RelPedidoProduto(DataPoolProduto.gerarProdutoXBurguer(), pedido, 1L),
                new RelPedidoProduto(DataPoolProduto.gerarProdutoXBurguer(), pedido, 2L));
    }
}
