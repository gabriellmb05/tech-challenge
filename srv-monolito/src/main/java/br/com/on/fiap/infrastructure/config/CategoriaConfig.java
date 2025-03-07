package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.CategoriaController;
import br.com.on.fiap.core.adapter.controller.impl.CategoriaControllerImpl;
import br.com.on.fiap.core.adapter.gateway.CategoriaGatewayImpl;
import br.com.on.fiap.core.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.core.adapter.presenter.impl.CategoriaPresenterImpl;
import br.com.on.fiap.core.application.gateway.CategoriaGateway;
import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import br.com.on.fiap.core.application.usecase.categoria.impl.CategoriaBuscaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaConfig {

    @Bean
    public CategoriaGateway categoriaGateway() {
        return new CategoriaGatewayImpl();
    }

    @Bean
    public CategoriaPresenter categoriaPresenter() {
        return new CategoriaPresenterImpl();
    }

    @Bean
    public CategoriaBuscaUseCase categoriaBuscaUseCase() {
        return new CategoriaBuscaUseCaseImpl(categoriaGateway());
    }

    @Bean
    public CategoriaController categoriaController() {
        return new CategoriaControllerImpl(categoriaBuscaUseCase(), categoriaPresenter());
    }
}
