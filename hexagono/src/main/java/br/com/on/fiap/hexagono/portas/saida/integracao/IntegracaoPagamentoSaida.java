package br.com.on.fiap.hexagono.portas.saida.integracao;

import br.com.on.fiap.hexagono.dominio.Pagamento;

public interface IntegracaoPagamentoSaida {

    void integracaoEnviaPagamento(Pagamento pagamento);
}
