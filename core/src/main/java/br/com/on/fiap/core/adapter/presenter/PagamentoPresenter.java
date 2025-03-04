package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.model.Pagamento;

public interface PagamentoPresenter {
    PagamentoRespostaDTO formatar(Pagamento pagamento);
}
