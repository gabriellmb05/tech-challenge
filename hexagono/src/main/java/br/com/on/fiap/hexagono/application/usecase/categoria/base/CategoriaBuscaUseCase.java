package br.com.on.fiap.hexagono.application.usecase.categoria.base;

import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public interface CategoriaBuscaUseCase {

    List<Categoria> buscaCategorias();
}
