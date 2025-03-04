package br.com.on.fiap.core.usecase.pagamento;

import br.com.on.fiap.core.domain.entity.Pagamento;

public interface PagamentoAtualizaUseCase {
    Pagamento atualizaPagamento(Pagamento pagamento);
}
