package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.datasource.*;
import br.com.on.fiap.core.adapter.gateway.*;
import br.com.on.fiap.core.application.gateway.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayBeanConfig {

    private final ClienteDataSource clienteDataSource;
    private final PagamentoDataSource pagamentoDataSource;
    private final PedidoDataSource pedidoDataSource;
    private final ProdutoDataSource produtoDataSource;
    private final PagamentoIntegracaoDataSource pagamentoIntegracaoDataSource;

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

    @Bean
    public PagamentoIntegracaoGateway pagamentoIntegracaoGateway() {
        return new PagamentoIntegracaoGatewayImpl(pagamentoIntegracaoDataSource);
    }
}
