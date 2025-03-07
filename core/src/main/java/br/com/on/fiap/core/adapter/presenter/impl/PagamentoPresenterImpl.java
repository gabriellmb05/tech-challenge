package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.PagamentoResposta;

public class PagamentoPresenterImpl implements PagamentoPresenter {

    @Override
    public PagamentoResposta formatar(Pagamento pagamento) {
        return PagamentoResposta.create(pagamento);
    }
}
