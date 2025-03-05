package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.Pagina;

public interface PaginacaoPresenter<M, R> {

    Pagina<R> formatar(Pagina<M> obj);
}
