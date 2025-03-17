package br.com.on.fiap.core.application.dto.resposta;

import br.com.on.fiap.core.domain.PedidoProduto;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoQuantidade {
    Integer getQuantidade();

    ProdutoResposta getProduto();

    static ProdutoQuantidade create(PedidoProduto pedidoProduto) {
        return new ProdutoQuantidade() {
            @Override
            public Integer getQuantidade() {
                return pedidoProduto.getQuantidade().intValue();
            }

            @Override
            public ProdutoResposta getProduto() {
                Produto produto = pedidoProduto.getProduto();
                return ProdutoResposta.create(produto);
            }
        };
    }
}
