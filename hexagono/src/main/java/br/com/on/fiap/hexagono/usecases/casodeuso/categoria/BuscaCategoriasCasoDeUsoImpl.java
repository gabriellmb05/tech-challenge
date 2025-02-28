package br.com.on.fiap.hexagono.usecases.casodeuso.categoria;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.categoria.BuscaCategoriaCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.categoria.CategoriaGateway;
import java.util.List;

public class BuscaCategoriasCasoDeUsoImpl implements BuscaCategoriaCasoDeUso {

    private final CategoriaGateway categoriaGateway;

    public BuscaCategoriasCasoDeUsoImpl(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    @Override
    public List<Categoria> buscaCategorias() {
        return categoriaGateway.buscaCategorias();
    }
}
