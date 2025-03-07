package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;

public interface PaginacaoPresenter<M, R> {

    PaginaResposta<R> formatar(PaginaResposta<M> obj);
}
