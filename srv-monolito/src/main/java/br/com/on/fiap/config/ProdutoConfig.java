package br.com.on.fiap.config;

import br.com.on.fiap.hexagono.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.adapter.gateway.impl.ProdutoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    private final ProdutoDataSource produtoDataSource;

    public ProdutoConfig(ProdutoDataSource produtoDataSource) {
        this.produtoDataSource = produtoDataSource;
    }

    @Bean
    public ProdutoGateway produtoGateway() {
        return new ProdutoGatewayImpl(produtoDataSource);
    }

    @Bean
    public ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase() {
        return new ProdutoBuscaPorIdUseCaseImpl(produtoGateway());
    }

    @Bean
    public ProdutoInsereUseCase produtoInsereUseCase() {
        return new ProdutoInsereUseCaseImpl(produtoGateway());
    }

    @Bean
    public ProdutoAlteraUseCase produtoAlteraUseCase() {
        return new ProdutoAlteraUseCaseImpl(produtoGateway());
    }

    @Bean
    public ProdutoDeletaUseCase produtoDeletaUseCase() {
        return new ProdutoDeletaUseCaseImpl(produtoGateway());
    }

    @Bean
    public ProdutoListaUseCase produtoListaUseCase() {
        return new ProdutoListaUseCaseImpl(produtoGateway());
    }

    @Bean
    public ProdutoValidaPedidoUseCase produtoValidaPedidoUseCase() {
        return new ProdutoValidaPedidoUseCaseImpl(produtoGateway());
    }
}
