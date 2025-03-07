package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.categoria.CategoriaResposta;
import br.com.on.fiap.core.domain.Categoria;
import java.util.List;

public interface CategoriaPresenter {
    CategoriaResposta formatar(List<Categoria> categorias);
}
