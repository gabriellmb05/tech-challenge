package br.com.on.fiap.core.application.usecase.pagamento;

import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.PagamentoSolicitacao;
import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.SituacaoPagamento;
import java.util.Map;

public interface PagamentoCriaUseCase {
    Pagamento criarPagamento(
            PagamentoSolicitacao pagamentoSolicitacao,
            SituacaoPagamento situacaoPagamento,
            Map<Produto, Long> valoresProdutos);
}
