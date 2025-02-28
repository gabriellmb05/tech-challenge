package br.com.on.fiap.hexagono.usecases.interfaces.saida.integracao;

import br.com.on.fiap.hexagono.entities.entidades.Pagamento;

public interface IntegracaoPagamentoSaida {

    void integracaoEnviaPagamento(Pagamento pagamento);
}
