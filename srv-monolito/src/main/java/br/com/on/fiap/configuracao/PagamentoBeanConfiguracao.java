package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.usecase.pagamento.base.PagamentoAtualizaUseCase;
import br.com.on.fiap.hexagono.usecase.pagamento.base.PagamentoValidaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoBeanConfiguracao {

    private final IntegracaoPagamentoSaida integracaoPagamentoSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public PagamentoBeanConfiguracao(
            IntegracaoPagamentoSaida integracaoPagamentoSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        this.integracaoPagamentoSaida = integracaoPagamentoSaida;
        this.persistePagamentoPortaSaida = persistePagamentoPortaSaida;
    }

    @Bean
    public PagamentoAtualizaUseCase atualizaPagamento() {
        return new br.com.on.fiap.hexagono.usecase.pagamento.PagamentoAtualizaUseCase(
                integracaoPagamentoSaida, persistePagamentoPortaSaida);
    }

    @Bean
    public PagamentoValidaUseCase validaPagamentoJaRealizado() {
        return new br.com.on.fiap.hexagono.usecase.pagamento.PagamentoValidaUseCase();
    }
}
