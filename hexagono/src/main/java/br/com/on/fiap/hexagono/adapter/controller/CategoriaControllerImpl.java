package br.com.on.fiap.hexagono.adapter.controller;

import br.com.on.fiap.hexagono.adapter.controller.base.CategoriaController;
import br.com.on.fiap.hexagono.adapter.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.base.CategoriaBasePresenter;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import br.com.on.fiap.hexagono.usecase.categoria.base.CategoriaBuscaUseCase;
import java.util.List;

public class CategoriaControllerImpl implements CategoriaController {

    private final CategoriaBuscaUseCase categoriaBuscaUseCase;
    private final CategoriaBasePresenter categoriaBasePresenter;

    public CategoriaControllerImpl(
            CategoriaBuscaUseCase categoriaBuscaUseCase, CategoriaBasePresenter categoriaBasePresenter) {
        this.categoriaBuscaUseCase = categoriaBuscaUseCase;
        this.categoriaBasePresenter = categoriaBasePresenter;
    }

    public CategoriaSaidaDTO buscaCategorias() {
        List<Categoria> categorias = categoriaBuscaUseCase.buscaCategorias();
        return categoriaBasePresenter.formatar(categorias);
    }
}
