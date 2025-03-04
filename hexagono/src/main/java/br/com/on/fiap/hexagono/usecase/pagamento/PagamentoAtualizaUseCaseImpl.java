package br.com.on.fiap.hexagono.usecase.pagamento;

import br.com.on.fiap.hexagono.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.usecase.pagamento.base.PagamentoAtualizaUseCase;

public class PagamentoAtualizaUseCaseImpl implements PagamentoAtualizaUseCase {

    private final PagamentoGateway pagamentoGateway;

    public PagamentoAtualizaUseCaseImpl(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pagamento atualizaPagamento(Pagamento pagamento) {
        pagamentoGateway.integracaoEnviaPagamento(pagamento);
        return pagamentoGateway.salvaPagamentoFinalizado(pagamento);
    }
}
