package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.CategoriaSaidaDTO;
import br.com.on.fiap.core.domain.entity.Categoria;
import java.util.List;

public interface CategoriaPresenter {
    CategoriaSaidaDTO formatar(List<Categoria> categorias);
}
