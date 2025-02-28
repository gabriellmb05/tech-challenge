package br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;

public interface DetalhaPedidoCasoDeUso {
    Pedido detalhaPedido(String protocolo);
}
