package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.adapter.controller.impl.ProdutoControllerImpl;
import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.application.usecase.produto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProdutoControllerBeanConfig {

    private final ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase;
    private final ProdutoListaUseCase produtoListaUseCase;
    private final ProdutoInsereUseCase produtoInsereUseCase;
    private final ProdutoAlteraUseCase produtoAlteraUseCase;
    private final ProdutoDeletaUseCase produtoDeletaUseCase;
    private final ProdutoPresenter produtoPresenter;

    @Bean
    public ProdutoController produtoController() {
        return new ProdutoControllerImpl(
                produtoBuscaPorIdUseCase,
                produtoListaUseCase,
                produtoInsereUseCase,
                produtoAlteraUseCase,
                produtoDeletaUseCase,
                produtoPresenter);
    }
}
