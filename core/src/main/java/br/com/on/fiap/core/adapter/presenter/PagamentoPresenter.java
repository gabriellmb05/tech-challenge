package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.PagamentoRespostaDTO;

public interface PagamentoPresenter {
    PagamentoRespostaDTO formatar(Pagamento pagamento);
}
