package br.com.on.fiap.hexagono.casodeuso.pedido.entrada;

import br.com.on.fiap.hexagono.entidades.Pagamento;
import br.com.on.fiap.hexagono.entidades.Pedido;

public interface InserePedidoCasoDeUso {
    Pedido inserir(Pedido pedido, Pagamento pagamento);
}
