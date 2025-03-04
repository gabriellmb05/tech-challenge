package br.com.on.fiap.hexagono.casodeuso.pagamento;

import br.com.on.fiap.hexagono.casodeuso.pagamento.entrada.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pagamento;

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
