package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.application.dto.resposta.pagamento.PagamentoResposta;
import br.com.on.fiap.core.domain.Pagamento;

public class PagamentoPresenterImpl implements PagamentoPresenter {

    @Override
    public PagamentoResposta formatar(Pagamento pagamento) {
        return PagamentoResposta.create(pagamento);
    }
}
