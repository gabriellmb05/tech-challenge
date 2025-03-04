package br.com.on.fiap.hexagono.adapter.gateway.base;

import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public interface CategoriaGateway {
    List<Categoria> buscaCategorias();
}
