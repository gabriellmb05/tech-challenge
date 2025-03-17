package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.ProdutoDeletaUseCase;

public class ProdutoDeletaUseCaseImpl implements ProdutoDeletaUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoDeletaUseCaseImpl(ProdutoGateway persisteProdutoPortaSaida) {
        this.produtoGateway = persisteProdutoPortaSaida;
    }

    @Override
    public void deleta(Long id) {
        produtoGateway.buscaProdutoPorId(id).ifPresentOrElse(produto -> produtoGateway.deletaProdutoPorId(id), () -> {
            throw new ProdutoNaoEncontradoExcecao(MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id);
        });
    }
}
