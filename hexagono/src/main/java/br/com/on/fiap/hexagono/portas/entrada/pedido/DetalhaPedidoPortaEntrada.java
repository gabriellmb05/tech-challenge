package br.com.on.fiap.hexagono.portas.entrada.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;

public interface DetalhaPedidoPortaEntrada {
    Pedido detalhaPedido(String protocolo);
}
