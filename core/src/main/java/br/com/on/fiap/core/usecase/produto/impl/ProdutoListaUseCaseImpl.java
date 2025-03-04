package br.com.on.fiap.core.usecase.produto.impl;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.Paginacao;
import br.com.on.fiap.core.domain.model.ProdutoFiltro;
import br.com.on.fiap.core.usecase.produto.ProdutoListaUseCase;
import br.com.on.fiap.core.domain.model.Produto;

public class ProdutoListaUseCaseImpl implements ProdutoListaUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoListaUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Pagina<Produto> listarComFiltro(ProdutoFiltro filtro, Paginacao paginacao) {
        return this.produtoGateway.listarComFiltros(filtro, paginacao);
    }
}
