package br.com.on.fiap.infrastructure.config;

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
public class PagamentoUseCaseBeanConfig {

    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PagamentoGateway pagamentoGateway;

    public PagamentoUseCaseBeanConfig(
            @Lazy PedidoDetalhaUseCase pedidoDetalhaUseCase, PagamentoGateway pagamentoGateway) {
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.pagamentoGateway = pagamentoGateway;
    }

    @Bean
    public PagamentoAtualizaUseCase pagamentoAtualizaUseCase() {
        return new PagamentoAtualizaUseCaseImpl(pedidoDetalhaUseCase, pagamentoValidaUseCase(), pagamentoGateway);
    }

    @Bean
    public PagamentoValidaUseCase pagamentoValidaUseCase() {
        return new PagamentoValidaUseCaseImpl();
    }

    @Bean
    public PagamentoCriaUseCase pagamentoCriaUseCase() {
        return new PagamentoCriaUseCaseImpl();
    }
}
