package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.ProdutoBuscaPorIdUseCase;
import br.com.on.fiap.core.domain.Produto;
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
