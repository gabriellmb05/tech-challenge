package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adapter.controller.CategoriaControllerImpl;
import br.com.on.fiap.hexagono.adapter.controller.base.CategoriaController;
import br.com.on.fiap.hexagono.adapter.gateway.CategoriaGatewayImpl;
import br.com.on.fiap.hexagono.adapter.gateway.base.CategoriaGateway;
import br.com.on.fiap.hexagono.adapter.presenter.CategoriaPresenter;
import br.com.on.fiap.hexagono.adapter.presenter.base.CategoriaBasePresenter;
import br.com.on.fiap.hexagono.usecase.categoria.CategoriaBuscaUseCase;
import br.com.on.fiap.hexagono.usecase.categoria.entrada.BuscaCategoriaCasoDeUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class CategoriaBeanConfiguracao {

    private final CategoriaGateway categoriaGateway;
    private final CategoriaBasePresenter categoriaBasePresenter;

    @Lazy
    public CategoriaBeanConfiguracao(CategoriaGateway categoriaGateway, CategoriaBasePresenter categoriaBasePresenter) {
        this.categoriaGateway = categoriaGateway;
        this.categoriaBasePresenter = categoriaBasePresenter;
    }

    @Bean
    public BuscaCategoriaCasoDeUso buscaCategorias() {
        return new CategoriaBuscaUseCase(categoriaGateway);
    }

    @Bean
    public CategoriaController categoriaControlador() {
        return new CategoriaControllerImpl(buscaCategorias(), categoriaBasePresenter);
    }

    @Bean
    public CategoriaGateway categoriaGateway() {
        return new CategoriaGatewayImpl();
    }

    @Bean
    public CategoriaBasePresenter categoriaApresentador() {
        return new CategoriaPresenter();
    }
}
