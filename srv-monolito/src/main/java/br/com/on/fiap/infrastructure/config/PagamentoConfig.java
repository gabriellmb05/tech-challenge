package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.PagamentoController;
import br.com.on.fiap.core.adapter.controller.impl.PagamentoControllerImpl;
import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.adapter.gateway.PagamentoGatewayImpl;
import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.adapter.presenter.impl.PagamentoPresenterImpl;
import br.com.on.fiap.core.application.gateway.PagamentoGateway;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoValidaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoAtualizaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoCriaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoValidaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class PagamentoConfig {

    private final PagamentoDataSource pagamentoDataSource;

    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;

    public PagamentoConfig(PagamentoDataSource pagamentoDataSource, @Lazy PedidoDetalhaUseCase pedidoDetalhaUseCase) {
        this.pagamentoDataSource = pagamentoDataSource;
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
    }

    @Bean
    public PagamentoGateway pagamentoGateway() {
        return new PagamentoGatewayImpl(pagamentoDataSource);
    }

    @Bean
    public PagamentoPresenter pagamentoPresenter() {
        return new PagamentoPresenterImpl();
    }

    @Bean
    public PagamentoAtualizaUseCase pagamentoAtualizaUseCase() {
        return new PagamentoAtualizaUseCaseImpl(pedidoDetalhaUseCase, pagamentoValidaUseCase(), pagamentoGateway());
    }

    @Bean
    public PagamentoValidaUseCase pagamentoValidaUseCase() {
        return new PagamentoValidaUseCaseImpl();
    }

    @Bean
    public PagamentoCriaUseCase pagamentoCriaUseCase() {
        return new PagamentoCriaUseCaseImpl();
    }

    @Bean
    public PagamentoController pagamentoController() {
        return new PagamentoControllerImpl(pagamentoAtualizaUseCase(), pagamentoPresenter());
    }
}
