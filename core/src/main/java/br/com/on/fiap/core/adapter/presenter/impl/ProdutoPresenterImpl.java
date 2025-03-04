package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.domain.model.ProdutoRespostaDTO;
import br.com.on.fiap.core.domain.model.Produto;

public class ProdutoPresenterImpl implements ProdutoPresenter {
    @Override
    public ProdutoRespostaDTO formatar(Produto produto) {
        return new ProdutoRespostaDTO(
                produto.getId(), produto.getNome(), produto.getCategoria().name(), produto.getPreco());
    }
}
