package br.com.on.fiap.core.application.usecase.categoria.impl;

import br.com.on.fiap.core.application.gateway.CategoriaGateway;
import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import br.com.on.fiap.core.domain.Categoria;
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
