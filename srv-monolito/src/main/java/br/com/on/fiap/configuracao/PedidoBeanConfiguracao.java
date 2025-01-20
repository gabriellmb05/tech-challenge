package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.pedido.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.pedido.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.pedido.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.pedido.AtualizaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.BuscaPedidosPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.DetalhaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguracao {

    @Bean
    public InserePedidoPortaEntrada inserePedido(
            PersisteClientePortaSaida persisteClientePortaSaida,
            PersistePedidoPortaSaida persistePedidoPortaSaida,
            PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida,
            PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida) {
        return new InserePedidoCasoDeUso(
                persisteClientePortaSaida,
                persistePedidoPortaSaida,
                persistePedidoProdutoPortaSaida,
                persistePagamentoPortaSaida,
                persistePedidoPagamentoPortaSaida);
    }

    @Bean
    public BuscaPedidosPortaEntrada listaPedidos(BuscaPedidosPortaSaida buscaPedidosPortaSaida) {
        return new BuscaPedidosCasoDeUso(buscaPedidosPortaSaida);
    }

    @Bean
    public DetalhaPedidoPortaEntrada detalhaPedido(DetalhaPedidoPortaSaida detalhaPedidoPortaSaida) {
        return new DetalhaPedidoCasoDeUso(detalhaPedidoPortaSaida);
    }

    @Bean
    public AtualizaPedidoPortaEntrada atualizaPedido(
            AtualizaPedidoPortaSaida atualizaPedidoPortaSaida, DetalhaPedidoPortaEntrada detalhaPedidoCasoDeUso) {
        return new AtualizaPedidoCasoDeUso(atualizaPedidoPortaSaida, detalhaPedidoCasoDeUso);
    }
}
