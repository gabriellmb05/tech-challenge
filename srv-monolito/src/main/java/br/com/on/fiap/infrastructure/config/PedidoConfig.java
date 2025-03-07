package br.com.on.fiap.infrastructure.config;

import br.com.on.fiap.core.adapter.controller.PedidoController;
import br.com.on.fiap.core.adapter.controller.impl.PedidoControllerImpl;
import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.adapter.gateway.impl.PedidoGatewayImpl;
import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.adapter.presenter.impl.PedidoPresenterImpl;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.*;
import br.com.on.fiap.core.application.usecase.pedido.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    private final ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase;
    private final PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase;
    private final PagamentoCriaUseCase pagamentoCriaUseCase;

    private final PedidoDataSource pedidoDataSource;

    public PedidoConfig(
            ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase,
            PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase,
            PagamentoCriaUseCase pagamentoCriaUseCase,
            PedidoDataSource pedidoDataSource) {
        this.clienteBuscaPorIdUseCase = clienteBuscaPorIdUseCase;
        this.pedidoValidaProdutoUseCase = pedidoValidaProdutoUseCase;
        this.pagamentoCriaUseCase = pagamentoCriaUseCase;
        this.pedidoDataSource = pedidoDataSource;
    }

    @Bean
    public PedidoGateway pedidoGateway() {
        return new PedidoGatewayImpl(pedidoDataSource);
    }

    public PedidoPresenter pedidoPresenter() {
        return new PedidoPresenterImpl();
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
        return new PedidoListaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoDetalhaUseCase pedidoDetalhaUseCase() {
        return new PedidoDetalhaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoAtualizaUseCase pedidoAtualizaUseCase() {
        return new PedidoAtualizaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoCriaUseCase pedidoCriaUseCase() {
        return new PedidoCriaUseCaseImpl();
    }

    @Bean
    public PedidoProdutoCriaRelacionamentoUseCase pedidoProdutoCriaRelacionamentoUseCase() {
        return new PedidoProdutoCriaRelacionamentoUseCaseImpl();
    }

    @Bean
    public PedidoSalvaUseCase pedidoSalvaUseCase() {
        return new PedidoSalvaUseCaseImpl(pedidoGateway());
    }

    @Bean
    public PedidoController pedidoController() {
        return new PedidoControllerImpl(
                pedidoInsereUseCase(), pedidoListaUseCase(), pedidoAtualizaUseCase(), pedidoPresenter());
    }
}
