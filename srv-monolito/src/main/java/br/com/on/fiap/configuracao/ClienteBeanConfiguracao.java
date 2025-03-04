package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adapter.controller.ClienteControllerImpl;
import br.com.on.fiap.hexagono.adapter.controller.base.ClienteController;
import br.com.on.fiap.hexagono.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.hexagono.adapter.gateway.ClienteGatewayImpl;
import br.com.on.fiap.hexagono.adapter.gateway.base.ClienteGateway;
import br.com.on.fiap.hexagono.adapter.presenter.ClientePresenter;
import br.com.on.fiap.hexagono.adapter.presenter.base.ClienteBasePresenter;
import br.com.on.fiap.hexagono.usecase.cliente.base.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.hexagono.usecase.cliente.base.ClienteInsereUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ClienteBeanConfiguracao {

    private final ClienteGateway clienteGateway;
    private final ClienteDataSource clienteDataSource;
    private final ClienteBasePresenter clientePresenter;

    @Lazy
    public ClienteBeanConfiguracao(
            ClienteGateway clienteGateway, ClienteDataSource clienteDataSource, ClienteBasePresenter clientePresenter) {
        this.clienteGateway = clienteGateway;
        this.clienteDataSource = clienteDataSource;
        this.clientePresenter = clientePresenter;
    }

    @Bean
    public ClienteBuscaPorCpfUseCase buscaCliente() {
        return new br.com.on.fiap.hexagono.usecase.cliente.ClienteBuscaPorCpfUseCase(clienteGateway);
    }

    @Bean
    public ClienteInsereUseCase insereCliente() {
        return new br.com.on.fiap.hexagono.usecase.cliente.ClienteInsereUseCase(clienteGateway);
    }

    @Bean
    public ClienteGateway persisteClienteGateway() {
        return new ClienteGatewayImpl(clienteDataSource);
    }

    @Bean
    public ClienteBasePresenter clienteApresentador() {
        return new ClientePresenter();
    }

    @Bean
    public ClienteController clienteControlador() {
        return new ClienteControllerImpl(insereCliente(), buscaCliente(), clientePresenter);
    }
}
