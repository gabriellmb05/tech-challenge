package br.com.on.fiap.hexagono.portas.entrada.pagamento;

import br.com.on.fiap.hexagono.dominio.Pagamento;

public interface ValidaPagamentoPortaEntrada {
    void validarPagamentoJaRealizado(Pagamento pagamento, String nrProtocolo);
}
