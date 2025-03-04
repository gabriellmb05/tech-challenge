package br.com.on.fiap.hexagono.usecase.categoria.impl;

import br.com.on.fiap.hexagono.adapter.gateway.CategoriaGateway;
import br.com.on.fiap.hexagono.usecase.categoria.CategoriaBuscaUseCase;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public class CategoriaBuscaUseCaseImpl implements CategoriaBuscaUseCase {

    private final CategoriaGateway categoriaGateway;

    public CategoriaBuscaUseCaseImpl(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    @Override
    public List<Categoria> buscaCategorias() {
        return categoriaGateway.buscaCategorias();
    }
}
