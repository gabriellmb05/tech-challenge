package br.com.on.fiap.hexagono.adapter.presenter.base;

import br.com.on.fiap.hexagono.adapter.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public interface CategoriaBasePresenter {
    CategoriaSaidaDTO formatar(List<Categoria> categorias);
}
