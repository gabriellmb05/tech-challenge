package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.Pagina;

public interface PaginacaoPresenter<M, R> {

    Pagina<R> formatar(Pagina<M> obj);
}
