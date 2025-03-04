package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.domain.entity.Pagamento;

public interface PagamentoDataSource {

    void integracaoEnviaPagamento(Pagamento pagamento);

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
