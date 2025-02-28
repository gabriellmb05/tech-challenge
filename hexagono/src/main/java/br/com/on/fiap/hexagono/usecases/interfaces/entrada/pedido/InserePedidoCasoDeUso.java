package br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pagamento;
import br.com.on.fiap.hexagono.entities.entidades.Pedido;

public interface InserePedidoCasoDeUso {
    Pedido inserir(Pedido pedido, Pagamento pagamento);
}
