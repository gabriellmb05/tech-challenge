package br.com.on.fiap.hexagono.casodeuso.categoria;

import br.com.on.fiap.hexagono.adaptadores.gateways.CategoriaGateway;
import br.com.on.fiap.hexagono.casodeuso.categoria.entrada.BuscaCategoriaCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Categoria;
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
