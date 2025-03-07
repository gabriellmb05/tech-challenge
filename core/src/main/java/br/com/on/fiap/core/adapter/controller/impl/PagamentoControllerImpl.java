package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.PagamentoController;
import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.application.dto.resposta.pagamento.PagamentoResposta;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.core.domain.Pagamento;

public class PagamentoControllerImpl implements PagamentoController {

    private final PagamentoAtualizaUseCase pagamentoAtualizaUseCase;

    private final PagamentoPresenter pagamentoPresenter;

    public PagamentoControllerImpl(
            PagamentoAtualizaUseCase pagamentoAtualizaUseCase, PagamentoPresenter pagamentoPresenter) {
        this.pagamentoAtualizaUseCase = pagamentoAtualizaUseCase;
        this.pagamentoPresenter = pagamentoPresenter;
    }

    @Override
    public PagamentoResposta atualizaPagamento(String nrProtocolo) {
        Pagamento pagamentoAtualizado = pagamentoAtualizaUseCase.atualizaPagamento(nrProtocolo);
        return pagamentoPresenter.formatar(pagamentoAtualizado);
    }
}
