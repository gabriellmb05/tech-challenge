package br.com.on.fiap.hexagono.application.usecase.produto.impl;

import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.application.usecase.produto.ProdutoAlteraUseCase;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;

public class ProdutoAlteraUseCaseImpl implements ProdutoAlteraUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoAlteraUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto alterar(Long id, Produto produto) {
        Produto produtoEncontrado = produtoGateway
                .buscaProdutoPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
        produtoEncontrado.atualizarDados(produto);
        return produtoGateway.salvaProduto(produtoEncontrado);
    }
}
