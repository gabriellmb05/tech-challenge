package br.com.on.fiap.datapool;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;

public class DataPoolRelPedidoProduto {

    public static List<RelPedidoProduto> gerarListaRelPedidoProduto(Pedido pedido) {
        return List.of(
                new RelPedidoProduto(DataPoolProduto.gerarProdutoXBurguer(), pedido, 1L),
                new RelPedidoProduto(DataPoolProduto.gerarProdutoXBurguer(), pedido, 2L));
    }
}
