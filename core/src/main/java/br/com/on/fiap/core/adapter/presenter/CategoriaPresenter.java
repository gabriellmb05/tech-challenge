package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.CategoriaRespostaDTO;
import br.com.on.fiap.core.domain.Categoria;
import java.util.List;

public interface CategoriaPresenter {
    CategoriaRespostaDTO formatar(List<Categoria> categorias);
}
