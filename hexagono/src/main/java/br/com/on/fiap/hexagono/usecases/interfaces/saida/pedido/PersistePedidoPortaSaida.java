package br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;

public interface PersistePedidoPortaSaida {

    Pedido salvaPedido(Pedido pedido);
}
