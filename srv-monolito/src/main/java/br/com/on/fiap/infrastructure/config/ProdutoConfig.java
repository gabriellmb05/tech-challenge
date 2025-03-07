package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.adapter.controller.impl.ProdutoControllerImpl;
import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.adapter.gateway.ProdutoGatewayImpl;
import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.adapter.presenter.impl.ProdutoPresenterImpl;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.pedido.PedidoValidaProdutoUseCase;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoValidaProdutoUseCaseImpl;
import br.com.on.fiap.core.application.usecase.produto.*;
import br.com.on.fiap.core.application.usecase.produto.impl.*;
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
    public ProdutoPresenter produtoPresenter() {
        return new ProdutoPresenterImpl();
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
    public PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase() {
        return new PedidoValidaProdutoUseCaseImpl(produtoGateway());
    }

    @Bean
    public ProdutoController produtoController() {
        return new ProdutoControllerImpl(
                produtoBuscaPorIdUseCase(),
                produtoListaUseCase(),
                produtoInsereUseCase(),
                produtoAlteraUseCase(),
                produtoDeletaUseCase(),
                produtoPresenter());
    }
}
