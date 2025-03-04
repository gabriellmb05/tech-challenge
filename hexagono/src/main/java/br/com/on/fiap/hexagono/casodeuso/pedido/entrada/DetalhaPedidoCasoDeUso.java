package br.com.on.fiap.hexagono.casodeuso.pedido.entrada;

import br.com.on.fiap.hexagono.entidades.Pedido;

public interface DetalhaPedidoCasoDeUso {
    Pedido detalhaPedido(String protocolo);
}
