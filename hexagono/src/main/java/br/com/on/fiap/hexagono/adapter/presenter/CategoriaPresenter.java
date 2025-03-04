package br.com.on.fiap.hexagono.adapter.presenter;

import br.com.on.fiap.hexagono.adapter.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;

public class CategoriaPresenter implements br.com.on.fiap.hexagono.adapter.presenter.base.CategoriaBasePresenter {

    @Override
    public CategoriaSaidaDTO formatar(List<Categoria> categorias) {
        List<String> nomeCategorias = categorias.stream().map(Categoria::name).toList();
        return new CategoriaSaidaDTO(nomeCategorias);
    }
}
