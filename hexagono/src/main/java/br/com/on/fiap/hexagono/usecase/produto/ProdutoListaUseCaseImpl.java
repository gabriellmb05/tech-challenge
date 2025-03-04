package br.com.on.fiap.hexagono.usecase.produto;

import br.com.on.fiap.hexagono.adapter.gateway.base.ProdutoGateway;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import br.com.on.fiap.hexagono.usecase.produto.base.ProdutoListaUseCase;
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
