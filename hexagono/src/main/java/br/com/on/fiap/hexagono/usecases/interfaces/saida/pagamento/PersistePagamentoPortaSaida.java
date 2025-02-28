package br.com.on.fiap.hexagono.usecases.interfaces.saida.pagamento;

import br.com.on.fiap.hexagono.entities.entidades.Pagamento;

public interface PersistePagamentoPortaSaida {

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
