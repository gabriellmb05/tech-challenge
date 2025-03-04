package br.com.on.fiap.core.usecase.produto.impl;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.usecase.produto.ProdutoInsereUseCase;
import br.com.on.fiap.core.domain.entity.Produto;
import br.com.on.fiap.core.domain.exception.ProdutoExistenteExcecao;
import br.com.on.fiap.core.domain.exception.message.MessageError;
import java.util.Optional;

public class ProdutoInsereUseCaseImpl implements ProdutoInsereUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoInsereUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto inserir(Produto produto) {
        Optional<Produto> produtoBancoDados = produtoGateway.buscaProdutoPorNome(produto.getNome());
        produtoBancoDados.ifPresent(p -> {
            throw new ProdutoExistenteExcecao(
                    MessageError.MSG_ERRO_PRODUTO_JA_CADASTRADO.getMensagem(), produto.getNome());
        });
        return produtoGateway.salvaProduto(produto);
    }
}
