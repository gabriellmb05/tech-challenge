package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adapter.controller.ClienteControllerImpl;
import br.com.on.fiap.hexagono.adapter.controller.base.ClienteController;
import br.com.on.fiap.hexagono.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.hexagono.adapter.gateway.ClienteGatewayImpl;
import br.com.on.fiap.hexagono.adapter.gateway.base.ClienteGateway;
import br.com.on.fiap.hexagono.adapter.presenter.ClientePresenterImpl;
import br.com.on.fiap.hexagono.adapter.presenter.base.ClientePresenter;
import br.com.on.fiap.hexagono.usecase.cliente.ClienteBuscaPorCpfUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.cliente.ClienteInsereUseCaseImpl;
import br.com.on.fiap.hexagono.usecase.cliente.base.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.hexagono.usecase.cliente.base.ClienteInsereUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ClienteBeanConfiguracao {

    private final ClienteGateway clienteGateway;
    private final ClienteDataSource clienteDataSource;
    private final ClientePresenter clientePresenter;

    @Lazy
    public ClienteBeanConfiguracao(
            ClienteGateway clienteGateway, ClienteDataSource clienteDataSource, ClientePresenter clientePresenter) {
        this.clienteGateway = clienteGateway;
        this.clienteDataSource = clienteDataSource;
        this.clientePresenter = clientePresenter;
    }

    @Bean
    public ClienteBuscaPorCpfUseCase buscaCliente() {
        return new ClienteBuscaPorCpfUseCaseImpl(clienteGateway);
    }

    @Bean
    public ClienteInsereUseCase insereCliente() {
        return new ClienteInsereUseCaseImpl(clienteGateway);
    }

    @Bean
    public ClienteGateway persisteClienteGateway() {
        return new ClienteGatewayImpl(clienteDataSource);
    }

    @Bean
    public ClientePresenter clienteApresentador() {
        return new ClientePresenterImpl();
    }

    @Bean
    public ClienteController clienteControlador() {
        return new ClienteControllerImpl(insereCliente(), buscaCliente(), clientePresenter);
    }
}
