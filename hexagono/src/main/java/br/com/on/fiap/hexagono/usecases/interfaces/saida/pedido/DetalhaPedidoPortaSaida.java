package br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import java.util.Optional;

public interface DetalhaPedidoPortaSaida {
    Optional<Pedido> detalhaPedido(String protocolo);
}
