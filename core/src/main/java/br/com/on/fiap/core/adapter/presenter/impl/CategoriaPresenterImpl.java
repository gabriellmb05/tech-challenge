package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.core.application.dto.resposta.CategoriaResposta;
import br.com.on.fiap.core.domain.Categoria;
import java.util.List;

public class CategoriaPresenterImpl implements CategoriaPresenter {

    @Override
    public CategoriaResposta formatar(List<Categoria> categorias) {
        List<String> nomeCategorias = categorias.stream().map(Categoria::name).toList();
        return new CategoriaResposta(nomeCategorias);
    }
}
