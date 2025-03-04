package br.com.on.fiap.core.usecase.pagamento;

import br.com.on.fiap.core.domain.entity.Pagamento;

public interface PagamentoValidaUseCase {
    void validarPagamentoJaRealizado(Pagamento pagamento, String nrProtocolo);
}
