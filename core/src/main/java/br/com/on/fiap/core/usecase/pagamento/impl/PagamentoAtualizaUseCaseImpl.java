package br.com.on.fiap.core.usecase.pagamento.impl;

import br.com.on.fiap.core.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.usecase.pagamento.PagamentoAtualizaUseCase;

public class PagamentoAtualizaUseCaseImpl implements PagamentoAtualizaUseCase {

    private final PagamentoGateway pagamentoGateway;

    public PagamentoAtualizaUseCaseImpl(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pagamento atualizaPagamento(Pagamento pagamento) {
        pagamentoGateway.integracaoEnviaPagamento(pagamento);
        return pagamentoGateway.salvaPagamentoFinalizado(pagamento);
    }
}
