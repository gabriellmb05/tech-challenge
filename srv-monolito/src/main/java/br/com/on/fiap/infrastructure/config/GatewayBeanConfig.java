package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.adapter.gateway.*;
import br.com.on.fiap.core.application.gateway.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeanConfig {

    private final ClienteDataSource clienteDataSource;
    private final PagamentoDataSource pagamentoDataSource;
    private final PedidoDataSource pedidoDataSource;
    private final ProdutoDataSource produtoDataSource;

    public GatewayBeanConfig(
            ClienteDataSource clienteDataSource,
            PagamentoDataSource pagamentoDataSource,
            PedidoDataSource pedidoDataSource,
            ProdutoDataSource produtoDataSource) {
        this.clienteDataSource = clienteDataSource;
        this.pagamentoDataSource = pagamentoDataSource;
        this.pedidoDataSource = pedidoDataSource;
        this.produtoDataSource = produtoDataSource;
    }

    @Bean
    public CategoriaGateway categoriaGateway() {
        return new CategoriaGatewayImpl();
    }

    @Bean
    public ClienteGateway clienteGateway() {
        return new ClienteGatewayImpl(clienteDataSource);
    }

    @Bean
    public PagamentoGateway pagamentoGateway() {
        return new PagamentoGatewayImpl(pagamentoDataSource);
    }

    @Bean
    public PedidoGateway pedidoGateway() {
        return new PedidoGatewayImpl(pedidoDataSource);
    }

    @Bean
    public ProdutoGateway produtoGateway() {
        return new ProdutoGatewayImpl(produtoDataSource);
    }
}
