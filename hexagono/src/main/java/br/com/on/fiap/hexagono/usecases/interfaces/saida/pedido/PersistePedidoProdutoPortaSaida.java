package br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido;

import br.com.on.fiap.hexagono.entities.entidades.RelPedidoProduto;
import java.util.List;

public interface PersistePedidoProdutoPortaSaida {

    void vincularPedido(List<RelPedidoProduto> pedidoProdutos);
}
