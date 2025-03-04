package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adaptadores.gateways.ClienteGateway;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.casodeuso.pedido.AtualizaPedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.BuscaPedidosCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.DetalhaPedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.InserePedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguracao {

    @Bean
    public InserePedidoCasoDeUso inserePedido(
            ClienteGateway clienteGateway,
            PersistePedidoPortaSaida persistePedidoPortaSaida,
            PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida,
            PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida) {
        return new InserePedidoCasoDeUsoImpl(
                clienteGateway,
                persistePedidoPortaSaida,
                persistePedidoProdutoPortaSaida,
                persistePagamentoPortaSaida,
                persistePedidoPagamentoPortaSaida);
    }

    @Bean
    public BuscaPedidosCasoDeUso listaPedidos(BuscaPedidosPortaSaida buscaPedidosPortaSaida) {
        return new BuscaPedidosCasoDeUsoImpl(buscaPedidosPortaSaida);
    }

    @Bean
    public DetalhaPedidoCasoDeUso detalhaPedido(DetalhaPedidoPortaSaida detalhaPedidoPortaSaida) {
        return new DetalhaPedidoCasoDeUsoImpl(detalhaPedidoPortaSaida);
    }

    @Bean
    public AtualizaPedidoCasoDeUso atualizaPedido(AtualizaPedidoPortaSaida atualizaPedidoPortaSaida) {
        return new AtualizaPedidoCasoDeUsoImpl(atualizaPedidoPortaSaida);
    }
}
