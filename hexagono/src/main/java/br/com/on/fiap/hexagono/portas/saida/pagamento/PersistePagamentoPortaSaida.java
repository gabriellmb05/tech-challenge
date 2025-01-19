package br.com.on.fiap.hexagono.portas.saida.pagamento;

import br.com.on.fiap.hexagono.dominio.Pagamento;

public interface PersistePagamentoPortaSaida {

    Pagamento salvaPagamento(Pagamento pagamento);

    Pagamento salvaPagamentoFinalizado(Pagamento pagamento);
}
