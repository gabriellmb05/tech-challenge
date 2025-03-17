package br.com.on.fiap.core.application.usecase.pagamento;

import br.com.on.fiap.core.domain.Pagamento;

public interface PagamentoValidaUseCase {
    void validarPagamentoJaRealizado(Pagamento pagamento, String nrProtocolo);
}
