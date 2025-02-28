package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.usecases.casodeuso.pagamento.AtualizaPagamentoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.casodeuso.pagamento.ValidaPagamentoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pagamento.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pagamento.ValidaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pagamento.PersistePagamentoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoBeanConfiguracao {

    @Bean
    public AtualizaPagamentoCasoDeUso atualizaPagamento(
            IntegracaoPagamentoSaida integracaoPagamentoSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        return new AtualizaPagamentoCasoDeUsoImpl(integracaoPagamentoSaida, persistePagamentoPortaSaida);
    }

    @Bean
    public ValidaPagamentoCasoDeUso validaPagamentoJaRealizado() {
        return new ValidaPagamentoCasoDeUsoImpl();
    }
}
