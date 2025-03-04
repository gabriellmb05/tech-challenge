package br.com.on.fiap.hexagono.casodeuso.pedido.saida;

import br.com.on.fiap.hexagono.entidades.RelPedidoProduto;
import java.util.List;

public interface PersistePedidoProdutoPortaSaida {

    void vincularPedido(List<RelPedidoProduto> pedidoProdutos);
}
