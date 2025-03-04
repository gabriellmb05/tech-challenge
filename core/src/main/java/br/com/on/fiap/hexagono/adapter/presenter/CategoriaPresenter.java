package br.com.on.fiap.hexagono.adapter.presenter;

import br.com.on.fiap.hexagono.application.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public interface CategoriaPresenter {
    CategoriaSaidaDTO formatar(List<Categoria> categorias);
}
