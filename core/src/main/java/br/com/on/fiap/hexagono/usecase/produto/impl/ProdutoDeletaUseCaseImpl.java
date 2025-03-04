package br.com.on.fiap.hexagono.usecase.produto.impl;

import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.usecase.produto.ProdutoDeletaUseCase;
import br.com.on.fiap.hexagono.domain.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;

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
