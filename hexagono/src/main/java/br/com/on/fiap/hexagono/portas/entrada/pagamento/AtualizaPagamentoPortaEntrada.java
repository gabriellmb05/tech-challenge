package br.com.on.fiap.hexagono.portas.entrada.pagamento;

import br.com.on.fiap.hexagono.dominio.Pagamento;

public interface AtualizaPagamentoPortaEntrada {
    Pagamento atualizaPagamento(String nrProtocolo);
}
