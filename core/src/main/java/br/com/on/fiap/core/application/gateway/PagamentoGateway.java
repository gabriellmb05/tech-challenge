package br.com.on.fiap.core.application.gateway;

import br.com.on.fiap.core.domain.Pagamento;

public interface PagamentoGateway {

    Pagamento salvaPagamento(Pagamento pagamento);
}
