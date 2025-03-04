package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casodeuso.pagamento.AtualizaPagamentoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pagamento.ValidaPagamentoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pagamento.entrada.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pagamento.entrada.ValidaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.PersistePagamentoPortaSaida;
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
