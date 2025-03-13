package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import br.com.on.fiap.core.domain.Produto;

public class ProdutoPresenterImpl implements ProdutoPresenter {
    @Override
    public ProdutoResposta formatar(Produto produto) {
        return ProdutoResposta.create(produto);
    }


    public PaginaResposta<ProdutoResposta> formatar(PaginaResposta<Produto> obj) {
        return obj.map(this::formatar);
    }
}
