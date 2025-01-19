package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.pagamento.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.pagamento.ValidaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.AtualizaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.ValidaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoBeanConfiguracao {

    @Bean
    public AtualizaPagamentoPortaEntrada atualizaPagamento(
            IntegracaoPagamentoSaida integracaoPagamentoSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        return new AtualizaPagamentoCasoDeUso(integracaoPagamentoSaida, persistePagamentoPortaSaida);
    }

    @Bean
    public ValidaPagamentoPortaEntrada validaPagamentoJaRealizado() {
        return new ValidaPagamentoCasoDeUso();
    }
}
