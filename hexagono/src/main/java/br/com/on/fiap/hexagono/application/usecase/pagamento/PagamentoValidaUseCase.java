package br.com.on.fiap.hexagono.application.usecase.pagamento;

import br.com.on.fiap.hexagono.domain.entity.Pagamento;

public interface PagamentoValidaUseCase {
    void validarPagamentoJaRealizado(Pagamento pagamento, String nrProtocolo);
}
