package br.com.on.fiap.hexagono.portas.saida.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import java.util.Optional;

public interface AtualizaPedidoPortaSaida {
    Optional<Pedido> atualizarPedido(String protocolo);
}
