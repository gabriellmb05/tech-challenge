package br.com.on.fiap.core.application.usecase.pagamento;

import br.com.on.fiap.core.application.dto.entrada.PagamentoSolicitacao;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import java.util.Map;

public interface PagamentoCriaUseCase {
    Pagamento criarPagamento(
            PagamentoSolicitacao pagamentoSolicitacao,
            SituacaoPagamento situacaoPagamento,
            Map<Produto, Long> valoresProdutos);
}
