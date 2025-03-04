package br.com.on.fiap.hexagono.casodeuso.pagamento.entrada;

import br.com.on.fiap.hexagono.entidades.Pagamento;

public interface ValidaPagamentoCasoDeUso {
    void validarPagamentoJaRealizado(Pagamento pagamento, String nrProtocolo);
}
