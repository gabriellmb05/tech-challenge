package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.PagamentoResposta;

public interface PagamentoPresenter {
    PagamentoResposta formatar(Pagamento pagamento);
}
