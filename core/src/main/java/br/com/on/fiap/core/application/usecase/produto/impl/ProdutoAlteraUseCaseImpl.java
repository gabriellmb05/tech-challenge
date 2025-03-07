package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoEntrada;
import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.ProdutoAlteraUseCase;
import br.com.on.fiap.core.domain.Produto;

public class ProdutoAlteraUseCaseImpl implements ProdutoAlteraUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoAlteraUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto alterar(Long id, ProdutoEntrada produtoEntrada) {
        Produto produtoEncontrado = produtoGateway
                .buscaProdutoPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
        produtoEncontrado.atualizarDados(produtoEntrada);
        return produtoGateway.salvaProduto(produtoEncontrado);
    }
}
