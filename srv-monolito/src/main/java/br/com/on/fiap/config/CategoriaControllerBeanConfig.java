package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.controller.CategoriaController;
import br.com.on.fiap.core.adapter.controller.impl.CategoriaControllerImpl;
import br.com.on.fiap.core.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaControllerBeanConfig {

    private final CategoriaBuscaUseCase categoriaBuscaUseCase;
    private final CategoriaPresenter categoriaPresenter;

    public CategoriaControllerBeanConfig(
            CategoriaBuscaUseCase categoriaBuscaUseCase, CategoriaPresenter categoriaPresenter) {
        this.categoriaBuscaUseCase = categoriaBuscaUseCase;
        this.categoriaPresenter = categoriaPresenter;
    }

    @Bean
    public CategoriaController categoriaController() {
        return new CategoriaControllerImpl(categoriaBuscaUseCase, categoriaPresenter);
    }
}
