package br.com.on.fiap.core.application.usecase.pagamento;

import br.com.on.fiap.core.domain.Pagamento;

public interface PagamentoAtualizaUseCase {
    Pagamento atualizaPagamento(String nrProtocolo);
}
