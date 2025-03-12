package br.com.on.fiap.config;

import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.*;
import br.com.on.fiap.core.application.usecase.pedido.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoUseCaseBeanConfig {

    private final ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase;
    private final PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase;
    private final PagamentoCriaUseCase pagamentoCriaUseCase;
    private final PedidoGateway pedidoGateway;

    public PedidoUseCaseBeanConfig(
            ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase,
            PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase,
            PagamentoCriaUseCase pagamentoCriaUseCase,
            PedidoGateway pedidoGateway) {
        this.clienteBuscaPorIdUseCase = clienteBuscaPorIdUseCase;
        this.pedidoValidaProdutoUseCase = pedidoValidaProdutoUseCase;
        this.pagamentoCriaUseCase = pagamentoCriaUseCase;
        this.pedidoGateway = pedidoGateway;
    }

    @Bean
    public PedidoInsereUseCase pedidoInsereUseCase() {
        return new PedidoInsereUseCaseImpl(
                clienteBuscaPorIdUseCase,
                pedidoValidaProdutoUseCase,
                pedidoCriaUseCase(),
                pagamentoCriaUseCase,
                pedidoProdutoCriaRelacionamentoUseCase(),
                pedidoSalvaUseCase());
    }

    @Bean
    public PedidoListaUseCase pedidoListaUseCase() {
        return new PedidoListaUseCaseImpl(pedidoGateway);
    }

    @Bean
    public PedidoDetalhaUseCase pedidoDetalhaUseCase() {
        return new PedidoDetalhaUseCaseImpl(pedidoGateway);
    }

    @Bean
    public PedidoAtualizaUseCase pedidoAtualizaUseCase() {
        return new PedidoAtualizaUseCaseImpl(pedidoGateway);
    }

    @Bean
    public PedidoSalvaUseCase pedidoSalvaUseCase() {
        return new PedidoSalvaUseCaseImpl(pedidoGateway);
    }

    @Bean
    public PedidoCriaUseCase pedidoCriaUseCase() {
        return new PedidoCriaUseCaseImpl();
    }

    @Bean
    public PedidoProdutoCriaRelacionamentoUseCase pedidoProdutoCriaRelacionamentoUseCase() {
        return new PedidoProdutoCriaRelacionamentoUseCaseImpl();
    }
}
