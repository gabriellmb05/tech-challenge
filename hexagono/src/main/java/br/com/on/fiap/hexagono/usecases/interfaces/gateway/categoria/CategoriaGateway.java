package br.com.on.fiap.hexagono.usecases.interfaces.gateway.categoria;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import java.util.List;

public interface CategoriaGateway {
    List<Categoria> buscaCategorias();
}
