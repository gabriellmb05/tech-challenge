package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.pedido.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.pedido.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.pedido.BuscaPedidosPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.BuscaPedidosPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.DetalhaPedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoProdutoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguracao {

    @Bean
    public InserePedidoPortaEntrada inserePedido(
            PersisteClientePortaSaida persisteClientePortaSaida,
            PersistePedidoPortaSaida persistePedidoPortaSaida,
            PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        return new InserePedidoCasoDeUso(
                persisteClientePortaSaida,
                persistePedidoPortaSaida,
                persistePedidoProdutoPortaSaida,
                persistePagamentoPortaSaida);
    }

    @Bean
    public BuscaPedidosPortaEntrada listaPedidos(BuscaPedidosPortaSaida buscaPedidosPortaSaida) {
        return new BuscaPedidosCasoDeUso(buscaPedidosPortaSaida);
    }

    @Bean
    public DetalhaPedidoCasoDeUso detalhaPedido(DetalhaPedidoPortaSaida detalhaPedidoPortaSaida) {
        return new DetalhaPedidoCasoDeUso(detalhaPedidoPortaSaida);
    }
}
