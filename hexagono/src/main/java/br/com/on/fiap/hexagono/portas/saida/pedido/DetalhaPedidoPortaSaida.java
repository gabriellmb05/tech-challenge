package br.com.on.fiap.hexagono.portas.saida.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import java.util.Optional;

public interface DetalhaPedidoPortaSaida {
	Optional<Pedido> detalhaPedido(String protocolo);
}
