package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adaptadores.gateways.ClienteGateway;
import br.com.on.fiap.hexagono.adaptadores.gateways.PedidoGateway;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.casodeuso.pedido.AtualizaPedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.BuscaPedidosCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.DetalhaPedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.InserePedidoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.InserePedidoCasoDeUso;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PedidoBeanConfiguracao {

    private final ClienteGateway clienteGateway;
    private final PedidoGateway pedidoGateway;

    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;


    @Bean
    public InserePedidoCasoDeUso inserePedido() {
        return new InserePedidoCasoDeUsoImpl(
                clienteGateway,
                            pedidoGateway,
                persistePagamentoPortaSaida);
    }

    @Bean
    public BuscaPedidosCasoDeUso listaPedidos() {
        return new BuscaPedidosCasoDeUsoImpl(pedidoGateway);
    }

    @Bean
    public DetalhaPedidoCasoDeUso detalhaPedido() {
        return new DetalhaPedidoCasoDeUsoImpl(pedidoGateway);
    }

    @Bean
    public AtualizaPedidoCasoDeUso atualizaPedido() {
        return new AtualizaPedidoCasoDeUsoImpl(pedidoGateway);
    }
}
