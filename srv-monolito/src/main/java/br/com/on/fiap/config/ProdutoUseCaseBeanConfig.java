package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.pedido.PedidoValidaProdutoUseCase;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoValidaProdutoUseCaseImpl;
import br.com.on.fiap.core.application.usecase.produto.*;
import br.com.on.fiap.core.application.usecase.produto.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoUseCaseBeanConfig {

    private final ProdutoGateway produtoGateway;

    public ProdutoUseCaseBeanConfig(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Bean
    public ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase() {
        return new ProdutoBuscaPorIdUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoInsereUseCase produtoInsereUseCase() {
        return new ProdutoInsereUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoAlteraUseCase produtoAlteraUseCase() {
        return new ProdutoAlteraUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoDeletaUseCase produtoDeletaUseCase() {
        return new ProdutoDeletaUseCaseImpl(produtoGateway);
    }

    @Bean
    public ProdutoListaUseCase produtoListaUseCase() {
        return new ProdutoListaUseCaseImpl(produtoGateway);
    }

    @Bean
    public PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase() {
        return new PedidoValidaProdutoUseCaseImpl(produtoGateway);
    }
}
