package br.com.on.fiap.hexagono.usecase.categoria;

import br.com.on.fiap.hexagono.adapter.gateway.base.CategoriaGateway;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public class CategoriaBuscaUseCase implements br.com.on.fiap.hexagono.usecase.categoria.base.CategoriaBuscaUseCase {

    private final CategoriaGateway categoriaGateway;

    public CategoriaBuscaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    @Override
    public List<Categoria> buscaCategorias() {
        return categoriaGateway.buscaCategorias();
    }
}
