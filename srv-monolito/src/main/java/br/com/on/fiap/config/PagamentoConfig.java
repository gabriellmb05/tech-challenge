package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.core.adapter.gateway.impl.PagamentoGatewayImpl;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoValidaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoAtualizaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoValidaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfig {

    private final PagamentoDataSource pagamentoDataSource;

    public PagamentoConfig(PagamentoDataSource pagamentoDataSource) {
        this.pagamentoDataSource = pagamentoDataSource;
    }

    @Bean
    public PagamentoGateway pagamentoGateway() {
        return new PagamentoGatewayImpl(pagamentoDataSource);
    }

    @Bean
    public PagamentoAtualizaUseCase pagamentoAtualizaUseCase() {
        return new PagamentoAtualizaUseCaseImpl(pagamentoGateway());
    }

    @Bean
    public PagamentoValidaUseCase pagamentoValidaUseCase() {
        return new PagamentoValidaUseCaseImpl();
    }
}
