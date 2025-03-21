package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.CategoriaController;
import br.com.on.fiap.core.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.core.application.dto.resposta.CategoriaResposta;
import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import br.com.on.fiap.core.domain.Categoria;
import java.util.List;

public class CategoriaControllerImpl implements CategoriaController {

    private final CategoriaBuscaUseCase categoriaBuscaUseCase;
    private final CategoriaPresenter categoriaPresenter;

    public CategoriaControllerImpl(CategoriaBuscaUseCase categoriaBuscaUseCase, CategoriaPresenter categoriaPresenter) {
        this.categoriaBuscaUseCase = categoriaBuscaUseCase;
        this.categoriaPresenter = categoriaPresenter;
    }

    public CategoriaResposta buscaCategorias() {
        List<Categoria> categorias = categoriaBuscaUseCase.buscaCategorias();
        return categoriaPresenter.formatar(categorias);
    }
}
