package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import br.com.on.fiap.core.application.exception.ProdutoExistenteExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.usecase.produto.ProdutoInsereUseCase;
import br.com.on.fiap.core.domain.Produto;
import java.util.Optional;

public class ProdutoInsereUseCaseImpl implements ProdutoInsereUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoInsereUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto inserir(ProdutoEntrada produtoEntrada) {
        Optional<Produto> produtoBancoDados = produtoGateway.buscaProdutoPorNome(produtoEntrada.getNome());
        produtoBancoDados.ifPresent(p -> {
            throw new ProdutoExistenteExcecao(
                    MessageError.MSG_ERRO_PRODUTO_JA_CADASTRADO.getMensagem(), produtoEntrada.getNome());
        });
        Produto produto = new Produto();
        produto.atualizarDados(produtoEntrada);
        return produtoGateway.salvaProduto(produto);
    }
}
