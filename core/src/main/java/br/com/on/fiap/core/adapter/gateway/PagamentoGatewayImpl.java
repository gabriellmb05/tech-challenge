package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.application.gateway.PagamentoGateway;
import br.com.on.fiap.core.domain.Pagamento;

public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoDataSource pagamentoDataSource;

    public PagamentoGatewayImpl(PagamentoDataSource pagamentoDataSource) {
        this.pagamentoDataSource = pagamentoDataSource;
    }

    @Override
    public Pagamento salvaPagamento(Pagamento pagamento) {
        return pagamentoDataSource.salvaPagamento(pagamento);
    }
}
