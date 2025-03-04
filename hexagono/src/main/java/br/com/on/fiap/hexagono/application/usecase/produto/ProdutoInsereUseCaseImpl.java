package br.com.on.fiap.hexagono.application.usecase.produto;

import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.exception.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
import br.com.on.fiap.hexagono.application.usecase.produto.base.ProdutoInsereUseCase;
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
