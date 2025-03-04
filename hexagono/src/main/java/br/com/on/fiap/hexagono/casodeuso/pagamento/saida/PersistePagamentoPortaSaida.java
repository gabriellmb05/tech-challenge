package br.com.on.fiap.hexagono.casodeuso.pagamento.saida;

import br.com.on.fiap.hexagono.entidades.Pagamento;

public interface PersistePagamentoPortaSaida {

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
