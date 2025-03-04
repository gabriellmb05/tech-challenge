package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.controller.ClienteController;
import br.com.on.fiap.core.adapter.controller.impl.ClienteControllerImpl;
import br.com.on.fiap.core.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.adapter.gateway.impl.ClienteGatewayImpl;
import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.adapter.presenter.impl.ClientePresenterImpl;
import br.com.on.fiap.core.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.usecase.cliente.ClienteInsereUseCase;
import br.com.on.fiap.core.usecase.cliente.impl.ClienteBuscaPorCpfUseCaseImpl;
import br.com.on.fiap.core.usecase.cliente.impl.ClienteInsereUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {

    private final ClienteDataSource clienteDataSource;

    public ClienteConfig(ClienteDataSource clienteDataSource) {
        this.clienteDataSource = clienteDataSource;
    }

    @Bean
    public ClienteGateway clienteGateway() {
        return new ClienteGatewayImpl(clienteDataSource);
    }

    @Bean
    public ClientePresenter clientePresenter() {
        return new ClientePresenterImpl();
    }

    @Bean
    public ClienteBuscaPorCpfUseCase buscaCliente() {
        return new ClienteBuscaPorCpfUseCaseImpl(clienteGateway());
    }

    @Bean
    public ClienteInsereUseCase insereCliente() {
        return new ClienteInsereUseCaseImpl(clienteGateway());
    }

    @Bean
    public ClienteController clienteControlador() {
        return new ClienteControllerImpl(insereCliente(), buscaCliente(), clientePresenter());
    }
}
