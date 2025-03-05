package br.com.on.fiap.core.application.usecase.pagamento;

import br.com.on.fiap.core.domain.model.Pagamento;

public interface PagamentoAtualizaUseCase {
    Pagamento atualizaPagamento(Pagamento pagamento);
}
