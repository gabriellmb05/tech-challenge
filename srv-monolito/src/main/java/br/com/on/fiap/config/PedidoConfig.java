package br.com.on.fiap.config;

import br.com.on.fiap.hexagono.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.hexagono.adapter.gateway.ClienteGateway;
import br.com.on.fiap.hexagono.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.hexagono.adapter.gateway.PedidoGateway;
import br.com.on.fiap.hexagono.adapter.gateway.impl.PedidoGatewayImpl;
import br.com.on.fiap.hexagono.application.usecase.pedido.impl.PedidoAtualizaUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.pedido.impl.PedidoListaUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.pedido.impl.PedidoDetalhaUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.pedido.impl.PedidoInsereUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoInsereUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    private final ClienteGateway clienteGateway;
    private final PagamentoGateway pagamentoGateway;

    private final PedidoDataSource pedidoDataSource;

    public PedidoConfig(ClienteGateway clienteGateway, PagamentoGateway pagamentoGateway, PedidoDataSource pedidoDataSource) {
        this.clienteGateway = clienteGateway;
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoDataSource = pedidoDataSource;
    }

    @Bean
    public PedidoGateway pedidoGateway() {
        return new PedidoGatewayImpl(pedidoDataSource);
    }

    @Bean
    public PedidoInsereUseCase pedidoInsereUseCase() {
        return new PedidoInsereUseCaseImpl(clienteGateway, pedidoGateway(), pagamentoGateway);
    }

    @Bean
    public PedidoListaUseCase listaPedidos() {
        return new PedidoListaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoDetalhaUseCase detalhaPedido() {
        return new PedidoDetalhaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoAtualizaUseCase atualizaPedido() {
        return new PedidoAtualizaUseCaseImpl(pedidoGateway());
    }
}
