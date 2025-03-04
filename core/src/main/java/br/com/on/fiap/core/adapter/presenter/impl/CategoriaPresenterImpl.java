package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.core.application.dto.CategoriaSaidaDTO;
import br.com.on.fiap.core.domain.entity.Categoria;
import java.util.List;

public class CategoriaPresenterImpl implements CategoriaPresenter {

    @Override
    public CategoriaSaidaDTO formatar(List<Categoria> categorias) {
        List<String> nomeCategorias = categorias.stream().map(Categoria::name).toList();
        return new CategoriaSaidaDTO(nomeCategorias);
    }
}
