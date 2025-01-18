package br.com.on.fiap.hexagono.portas.saida.pedido;

import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;

public interface PersistePedidoProdutoPortaSaida {

    void vincularPedido(List<RelPedidoProduto> pedidoProdutos);
}
