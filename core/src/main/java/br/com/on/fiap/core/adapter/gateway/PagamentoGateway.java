package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.domain.entity.Pagamento;

public interface PagamentoGateway {

    void integracaoEnviaPagamento(Pagamento pagamento);

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
