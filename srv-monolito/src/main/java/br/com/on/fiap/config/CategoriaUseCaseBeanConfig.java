package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.application.gateway.CategoriaGateway;
import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import br.com.on.fiap.core.application.usecase.categoria.impl.CategoriaBuscaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaUseCaseBeanConfig {

    private final CategoriaGateway categoriaGateway;

    public CategoriaUseCaseBeanConfig(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    @Bean
    public CategoriaBuscaUseCase categoriaBuscaUseCase() {
        return new CategoriaBuscaUseCaseImpl(categoriaGateway);
    }
}
