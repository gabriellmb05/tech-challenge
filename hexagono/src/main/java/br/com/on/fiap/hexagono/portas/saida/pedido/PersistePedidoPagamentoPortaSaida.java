package br.com.on.fiap.hexagono.portas.saida.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;

public interface PersistePedidoPagamentoPortaSaida {

    void salvaPedidoPagamento(Pedido pedido);
}
