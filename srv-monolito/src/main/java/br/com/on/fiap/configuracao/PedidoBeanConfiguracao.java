package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.usecases.casodeuso.pedido.AtualizaPedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.casodeuso.pedido.BuscaPedidosCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.casodeuso.pedido.DetalhaPedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.casodeuso.pedido.InserePedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente.ClienteGateway;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.*;
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
