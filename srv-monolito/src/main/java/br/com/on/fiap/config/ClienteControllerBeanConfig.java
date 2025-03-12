package br.com.on.fiap.config;

import br.com.on.fiap.core.adapter.controller.ClienteController;
import br.com.on.fiap.core.adapter.controller.impl.ClienteControllerImpl;
import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteControllerBeanConfig {

    private final ClienteInsereUseCase clienteInsereUseCase;
    private final ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase;
    private final ClientePresenter clientePresenter;

    public ClienteControllerBeanConfig(
            ClienteInsereUseCase clienteInsereUseCase,
            ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase,
            ClientePresenter clientePresenter) {
        this.clienteInsereUseCase = clienteInsereUseCase;
        this.clienteBuscaPorCpfUseCase = clienteBuscaPorCpfUseCase;
        this.clientePresenter = clientePresenter;
    }

    @Bean
    public ClienteController clienteControlador() {
        return new ClienteControllerImpl(clienteInsereUseCase, clienteBuscaPorCpfUseCase, clientePresenter);
    }
}
