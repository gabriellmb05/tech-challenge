package br.com.on.fiap.core.adapter.gateway.impl;

import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.core.domain.Pagamento;

public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoDataSource pagamentoDataSource;

    public PagamentoGatewayImpl(PagamentoDataSource pagamentoDataSource) {
        this.pagamentoDataSource = pagamentoDataSource;
    }

    @Override
    public void integracaoEnviaPagamento(Pagamento pagamento) {
        pagamentoDataSource.integracaoEnviaPagamento(pagamento);
    }

    @Override
    public Pagamento salvaPagamento(Pagamento pagamento) {
        return pagamentoDataSource.salvaPagamento(pagamento);
    }
}
