package br.com.on.fiap.config;

import br.com.on.fiap.hexagono.adapter.controller.impl.CategoriaControllerImpl;
import br.com.on.fiap.hexagono.adapter.controller.CategoriaController;
import br.com.on.fiap.hexagono.adapter.gateway.impl.CategoriaGatewayImpl;
import br.com.on.fiap.hexagono.adapter.gateway.CategoriaGateway;
import br.com.on.fiap.hexagono.adapter.presenter.impl.CategoriaPresenterImpl;
import br.com.on.fiap.hexagono.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.hexagono.application.usecase.categoria.impl.CategoriaBuscaUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.categoria.CategoriaBuscaUseCase;
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
