package br.com.on.fiap.hexagono.usecase.produto;

import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
import br.com.on.fiap.hexagono.usecase.produto.base.ProdutoBuscaPorIdUseCase;
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
