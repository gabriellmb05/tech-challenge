package br.com.on.fiap.hexagono.adapter.gateway;

import br.com.on.fiap.hexagono.domain.entity.Pagamento;

public interface PagamentoGateway {

    void integracaoEnviaPagamento(Pagamento pagamento);

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
