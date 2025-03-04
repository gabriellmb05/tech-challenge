package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.CategoriaRespostaDTO;
import br.com.on.fiap.core.domain.model.Categoria;
import java.util.List;

public interface CategoriaPresenter {
    CategoriaRespostaDTO formatar(List<Categoria> categorias);
}
