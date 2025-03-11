package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.PagamentoController;
import br.com.on.fiap.core.adapter.controller.impl.PagamentoControllerImpl;
import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoControllerBeanConfig {

    private final PagamentoAtualizaUseCase pagamentoAtualizaUseCase;
    private final PagamentoPresenter pagamentoPresenter;

    public PagamentoControllerBeanConfig(
            PagamentoAtualizaUseCase pagamentoAtualizaUseCase, PagamentoPresenter pagamentoPresenter) {
        this.pagamentoAtualizaUseCase = pagamentoAtualizaUseCase;
        this.pagamentoPresenter = pagamentoPresenter;
    }

    @Bean
    public PagamentoController pagamentoController() {
        return new PagamentoControllerImpl(pagamentoAtualizaUseCase, pagamentoPresenter);
    }
}
