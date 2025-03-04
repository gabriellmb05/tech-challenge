package br.com.on.fiap.core.usecase.produto.impl;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.domain.entity.Produto;
import br.com.on.fiap.core.domain.entity.ProdutoFiltro;
import br.com.on.fiap.core.usecase.produto.ProdutoListaUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProdutoListaUseCaseImpl implements ProdutoListaUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoListaUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable) {
        return this.produtoGateway.listarComFiltros(filtro, pageable);
    }
}
