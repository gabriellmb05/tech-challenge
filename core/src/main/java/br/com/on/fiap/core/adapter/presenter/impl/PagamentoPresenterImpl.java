package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.application.dto.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.entity.Pagamento;

public class PagamentoPresenterImpl implements PagamentoPresenter {

    @Override
    public PagamentoRespostaDTO formatar(Pagamento pagamento) {
        return new PagamentoRespostaDTO(
                pagamento.getPagId(),
                pagamento.getVlCompra(),
                pagamento.getStPagamento(),
                pagamento.getTpPagamento(),
                pagamento.getDhPagamento());
    }
}
