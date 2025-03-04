package br.com.on.fiap.core.usecase.categoria.impl;

import br.com.on.fiap.core.adapter.gateway.CategoriaGateway;
import br.com.on.fiap.core.domain.entity.Categoria;
import br.com.on.fiap.core.usecase.categoria.CategoriaBuscaUseCase;
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
