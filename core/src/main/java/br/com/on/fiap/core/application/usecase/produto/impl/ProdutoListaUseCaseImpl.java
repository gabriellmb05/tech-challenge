package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.dto.Pagina;
import br.com.on.fiap.core.application.dto.Paginacao;
import br.com.on.fiap.core.application.dto.ProdutoFiltro;
import br.com.on.fiap.core.application.usecase.produto.ProdutoListaUseCase;
import br.com.on.fiap.core.domain.entity.Produto;

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
