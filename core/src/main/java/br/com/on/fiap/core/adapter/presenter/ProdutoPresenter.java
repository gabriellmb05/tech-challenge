package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoPresenter extends PaginacaoPresenter<Produto, ProdutoResposta> {
    ProdutoResposta formatar(Produto produto);
}
