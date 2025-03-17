package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.adapter.datasource.PagamentoIntegracaoDataSource;
import br.com.on.fiap.core.application.gateway.PagamentoIntegracaoGateway;
import br.com.on.fiap.core.domain.Pagamento;

public class PagamentoIntegracaoGatewayImpl implements PagamentoIntegracaoGateway {

    private final PagamentoIntegracaoDataSource pagamentoIntegracaoDataSource;

    public PagamentoIntegracaoGatewayImpl(PagamentoIntegracaoDataSource pagamentoIntegracaoDataSource) {
        this.pagamentoIntegracaoDataSource = pagamentoIntegracaoDataSource;
    }

    @Override
    public void integracaoEnviaPagamento(Pagamento pagamento) {
        pagamentoIntegracaoDataSource.integracaoEnviaPagamento(pagamento);
    }
}
