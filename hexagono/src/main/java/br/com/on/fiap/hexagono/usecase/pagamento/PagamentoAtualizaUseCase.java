package br.com.on.fiap.hexagono.usecase.pagamento;

import br.com.on.fiap.hexagono.domain.entity.Pagamento;

public class PagamentoAtualizaUseCase
        implements br.com.on.fiap.hexagono.usecase.pagamento.base.PagamentoAtualizaUseCase {

    private final IntegracaoPagamentoSaida integracaoPagamentoSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public PagamentoAtualizaUseCase(
            IntegracaoPagamentoSaida integracaoPagamentoSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        this.integracaoPagamentoSaida = integracaoPagamentoSaida;
        this.persistePagamentoPortaSaida = persistePagamentoPortaSaida;
    }

    @Override
    public Pagamento atualizaPagamento(Pagamento pagamento) {
        integracaoPagamentoSaida.integracaoEnviaPagamento(pagamento);
        return persistePagamentoPortaSaida.salvaPagamentoFinalizado(pagamento);
    }
}
