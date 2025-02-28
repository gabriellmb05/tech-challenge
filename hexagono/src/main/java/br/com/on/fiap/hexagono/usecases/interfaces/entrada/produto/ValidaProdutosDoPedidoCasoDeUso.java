package br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;

public interface ValidaProdutosDoPedidoCasoDeUso {
    Pedido validarProdutosDoPedido(Pedido pedido);
}
