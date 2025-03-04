package br.com.on.fiap.hexagono.adapter.datasource;

import br.com.on.fiap.hexagono.domain.entity.Pagamento;

public interface PagamentoDataSource {

    void integracaoEnviaPagamento(Pagamento pagamento);

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
