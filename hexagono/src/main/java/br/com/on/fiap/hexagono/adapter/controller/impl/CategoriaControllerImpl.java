package br.com.on.fiap.hexagono.adapter.controller.impl;

import br.com.on.fiap.hexagono.adapter.controller.CategoriaController;
import br.com.on.fiap.hexagono.usecase.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import br.com.on.fiap.hexagono.usecase.categoria.base.CategoriaBuscaUseCase;
import java.util.List;

public class CategoriaControllerImpl implements CategoriaController {

    private final CategoriaBuscaUseCase categoriaBuscaUseCase;
    private final CategoriaPresenter categoriaPresenter;

    public CategoriaControllerImpl(CategoriaBuscaUseCase categoriaBuscaUseCase, CategoriaPresenter categoriaPresenter) {
        this.categoriaBuscaUseCase = categoriaBuscaUseCase;
        this.categoriaPresenter = categoriaPresenter;
    }

    public CategoriaSaidaDTO buscaCategorias() {
        List<Categoria> categorias = categoriaBuscaUseCase.buscaCategorias();
        return categoriaPresenter.formatar(categorias);
    }
}
