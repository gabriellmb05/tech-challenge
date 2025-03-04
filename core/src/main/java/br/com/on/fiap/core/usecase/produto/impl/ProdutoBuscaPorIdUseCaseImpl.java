package br.com.on.fiap.core.usecase.produto.impl;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.usecase.produto.ProdutoBuscaPorIdUseCase;
import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.domain.exception.message.MessageError;
import java.util.Optional;

public class ProdutoBuscaPorIdUseCaseImpl implements ProdutoBuscaPorIdUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoBuscaPorIdUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto buscar(Long id) {
        Optional<Produto> produtoBancoDados = produtoGateway.buscaProdutoPorId(id);
        return produtoBancoDados.orElseThrow(
                () -> new ProdutoNaoEncontradoExcecao(MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
    }
}
