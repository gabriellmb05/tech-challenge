package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adapter.controller.CategoriaControllerImpl;
import br.com.on.fiap.hexagono.adapter.controller.base.CategoriaController;
import br.com.on.fiap.hexagono.adapter.gateway.CategoriaGatewayImpl;
import br.com.on.fiap.hexagono.adapter.gateway.base.CategoriaGateway;
import br.com.on.fiap.hexagono.adapter.presenter.CategoriaPresenterImpl;
import br.com.on.fiap.hexagono.adapter.presenter.base.CategoriaPresenter;
import br.com.on.fiap.hexagono.usecase.categoria.CategoriaBuscaUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.categoria.entrada.BuscaCategoriaCasoDeUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class CategoriaBeanConfiguracao {

    private final CategoriaGateway categoriaGateway;
    private final CategoriaPresenter categoriaPresenter;

    @Lazy
    public CategoriaBeanConfiguracao(CategoriaGateway categoriaGateway, CategoriaPresenter categoriaPresenter) {
        this.categoriaGateway = categoriaGateway;
        this.categoriaPresenter = categoriaPresenter;
    }

    @Bean
    public BuscaCategoriaCasoDeUso buscaCategorias() {
        return new CategoriaBuscaUseCaseImpl(categoriaGateway);
    }

    @Bean
    public CategoriaController categoriaControlador() {
        return new CategoriaControllerImpl(buscaCategorias(), categoriaPresenter);
    }

    @Bean
    public CategoriaGateway categoriaGateway() {
        return new CategoriaGatewayImpl();
    }

    @Bean
    public CategoriaPresenter categoriaApresentador() {
        return new CategoriaPresenterImpl();
    }
}
