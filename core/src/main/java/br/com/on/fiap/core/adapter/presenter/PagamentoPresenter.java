package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.entity.Pagamento;

public interface PagamentoPresenter {
    PagamentoRespostaDTO formatar(Pagamento pagamento);
}
