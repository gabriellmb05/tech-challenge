package br.com.on.datapool;

import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.PedidoProduto;
import java.util.List;

public class DataPoolRelPedidoProduto {

    public static List<PedidoProduto> gerarListaRelPedidoProduto(Pedido pedido) {
        return List.of(
                new PedidoProduto(DataPoolProduto.gerarProdutoXBurguer(), pedido, 1L),
                new PedidoProduto(DataPoolProduto.gerarProdutoXBurguer(), pedido, 2L));
    }
}
