package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.pagamento.PagamentoResposta;
import br.com.on.fiap.core.domain.Pagamento;

public interface PagamentoPresenter {
    PagamentoResposta formatar(Pagamento pagamento);
}
