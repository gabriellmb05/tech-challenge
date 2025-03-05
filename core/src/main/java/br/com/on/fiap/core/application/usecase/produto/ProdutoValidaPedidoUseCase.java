package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.domain.model.Pedido;

public interface ProdutoValidaPedidoUseCase {
    Pedido validarProdutosDoPedido(Pedido pedido);
}
