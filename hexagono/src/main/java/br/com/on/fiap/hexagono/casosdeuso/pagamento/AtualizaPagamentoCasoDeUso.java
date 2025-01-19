package br.com.on.fiap.hexagono.casosdeuso.pagamento;

import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.AtualizaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;

public class AtualizaPagamentoCasoDeUso implements AtualizaPagamentoPortaEntrada {

    private final IntegracaoPagamentoSaida integracaoPagamentoSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public AtualizaPagamentoCasoDeUso(
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
