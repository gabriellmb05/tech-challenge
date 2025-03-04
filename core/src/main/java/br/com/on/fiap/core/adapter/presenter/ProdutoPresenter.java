package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.ProdutoRespostaDTO;
import br.com.on.fiap.core.domain.model.Produto;

public interface ProdutoPresenter {
    ProdutoRespostaDTO formatar(Produto produto);
}
