package br.com.on.fiap.hexagono.usecases.casodeuso.pagamento;

import br.com.on.fiap.hexagono.entities.entidades.Pagamento;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pagamento.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pagamento.PersistePagamentoPortaSaida;

public class AtualizaPagamentoCasoDeUsoImpl implements AtualizaPagamentoCasoDeUso {

    private final IntegracaoPagamentoSaida integracaoPagamentoSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public AtualizaPagamentoCasoDeUsoImpl(
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
