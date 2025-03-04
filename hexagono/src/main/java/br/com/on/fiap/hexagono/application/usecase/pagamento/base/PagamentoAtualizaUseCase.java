package br.com.on.fiap.hexagono.application.usecase.pagamento.base;

import br.com.on.fiap.hexagono.domain.entity.Pagamento;

public interface PagamentoAtualizaUseCase {
    Pagamento atualizaPagamento(Pagamento pagamento);
}
