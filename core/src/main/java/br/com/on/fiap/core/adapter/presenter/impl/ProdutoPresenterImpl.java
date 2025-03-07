package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.ProdutoResposta;

public class ProdutoPresenterImpl implements ProdutoPresenter {
    @Override
    public ProdutoResposta formatar(Produto produto) {
        return ProdutoResposta.create(produto);
    }

    @Override
    public Pagina<ProdutoResposta> formatar(Pagina<Produto> obj) {
        return obj.map(this::formatar);
    }
}
