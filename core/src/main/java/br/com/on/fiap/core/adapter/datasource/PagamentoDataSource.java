package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.domain.Pagamento;

public interface PagamentoDataSource {

    Pagamento salvaPagamento(Pagamento pagamento);
}
