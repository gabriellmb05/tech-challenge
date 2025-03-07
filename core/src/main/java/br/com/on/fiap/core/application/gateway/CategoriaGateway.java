package br.com.on.fiap.core.application.gateway;

import br.com.on.fiap.core.domain.Categoria;
import java.util.List;

public interface CategoriaGateway {
    List<Categoria> buscaCategorias();
}
