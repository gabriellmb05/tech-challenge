package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.ProdutoResposta;

public interface ProdutoPresenter extends PaginacaoPresenter<Produto, ProdutoResposta> {
    ProdutoResposta formatar(Produto produto);
}
