package br.com.on.fiap.hexagono.adapter.gateway;

import br.com.on.fiap.hexagono.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.hexagono.adapter.gateway.base.PagamentoGateway;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;

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

    @Override
    public Pagamento salvaPagamentoFinalizado(Pagamento pagamento) {
        return pagamentoDataSource.salvaPagamentoFinalizado(pagamento);
    }
}
