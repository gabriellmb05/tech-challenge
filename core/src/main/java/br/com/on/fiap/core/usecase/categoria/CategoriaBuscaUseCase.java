package br.com.on.fiap.core.usecase.categoria;

import br.com.on.fiap.core.domain.model.Categoria;
import java.util.List;

public interface CategoriaBuscaUseCase {

    List<Categoria> buscaCategorias();
}
