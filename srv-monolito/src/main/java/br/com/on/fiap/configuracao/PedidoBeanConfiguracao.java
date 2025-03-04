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
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PedidoBeanConfiguracao {

    private final ClienteGateway clienteGateway;

    private final PersistePedidoPortaSaida persistePedidoPortaSaida;
    private final PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;
    private final PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida;

    private final BuscaPedidosPortaSaida buscaPedidosPortaSaida;
    private final DetalhaPedidoPortaSaida detalhaPedidoPortaSaida;
    private final AtualizaPedidoPortaSaida atualizaPedidoPortaSaida;

    @Bean
    public InserePedidoCasoDeUso inserePedido() {
        return new InserePedidoCasoDeUsoImpl(
                clienteGateway,
                persistePedidoPortaSaida,
                persistePedidoProdutoPortaSaida,
                persistePagamentoPortaSaida,
                persistePedidoPagamentoPortaSaida);
    }

    @Bean
    public BuscaPedidosCasoDeUso listaPedidos() {
        return new BuscaPedidosCasoDeUsoImpl(buscaPedidosPortaSaida);
    }

    @Bean
    public DetalhaPedidoCasoDeUso detalhaPedido() {
        return new DetalhaPedidoCasoDeUsoImpl(detalhaPedidoPortaSaida);
    }

    @Bean
    public AtualizaPedidoCasoDeUso atualizaPedido() {
        return new AtualizaPedidoCasoDeUsoImpl(atualizaPedidoPortaSaida);
    }
}
