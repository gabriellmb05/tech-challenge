package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.adapter.gateway.impl.PedidoGatewayImpl;
import br.com.on.fiap.core.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.core.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.usecase.pedido.impl.PedidoAtualizaUseCaseImpl;
import br.com.on.fiap.core.usecase.pedido.impl.PedidoDetalhaUseCaseImpl;
import br.com.on.fiap.core.usecase.pedido.impl.PedidoInsereUseCaseImpl;
import br.com.on.fiap.core.usecase.pedido.impl.PedidoListaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    private final ClienteGateway clienteGateway;
    private final PagamentoGateway pagamentoGateway;

    private final PedidoDataSource pedidoDataSource;

    public PedidoConfig(
            ClienteGateway clienteGateway, PagamentoGateway pagamentoGateway, PedidoDataSource pedidoDataSource) {
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
    public PedidoListaUseCase pedidoListaUseCase() {
        return new PedidoListaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoDetalhaUseCase pedidoDetalhaUseCase() {
        return new PedidoDetalhaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoAtualizaUseCase pedidoAtualizaUseCase() {
        return new PedidoAtualizaUseCaseImpl(pedidoGateway());
    }
}
