package br.com.on.fiap.hexagono.casodeuso.pagamento.saida;

import br.com.on.fiap.hexagono.entidades.Pagamento;

public interface IntegracaoPagamentoSaida {

    void integracaoEnviaPagamento(Pagamento pagamento);
}
