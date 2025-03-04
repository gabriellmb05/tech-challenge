package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.ProdutoRespostaDTO;
import br.com.on.fiap.core.domain.entity.Produto;

public interface ProdutoPresenter {
    ProdutoRespostaDTO formatar(Produto produto);
}
