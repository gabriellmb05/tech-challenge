package br.com.on.fiap.hexagono.portas.entrada.produto;

import br.com.on.fiap.hexagono.dominio.Pedido;

public interface ValidaProdutosDoPedidoPortaEntrada {
    Pedido validarProdutosDoPedido(Pedido pedido);
}
