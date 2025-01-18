package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.pagamento.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.AtualizaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.DetalhaPedidoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoBeanConfiguracao {

    @Bean
    public AtualizaPagamentoPortaEntrada atualizaPagamento(
            IntegracaoPagamentoSaida integracaoPagamentoSaida,
            DetalhaPedidoPortaSaida detalhaPedidoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        return new AtualizaPagamentoCasoDeUso(
                integracaoPagamentoSaida, detalhaPedidoPortaSaida, persistePagamentoPortaSaida);
    }
}
