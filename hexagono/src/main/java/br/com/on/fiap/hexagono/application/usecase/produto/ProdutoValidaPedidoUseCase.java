package br.com.on.fiap.hexagono.application.usecase.produto;

import br.com.on.fiap.hexagono.domain.entity.Pedido;

public interface ProdutoValidaPedidoUseCase {
    Pedido validarProdutosDoPedido(Pedido pedido);
}
