package br.com.on.fiap.config;

import br.com.on.fiap.core.application.gateway.ClienteGateway;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;
import br.com.on.fiap.core.application.usecase.cliente.impl.ClienteBuscaPorCpfUseCaseImpl;
import br.com.on.fiap.core.application.usecase.cliente.impl.ClienteBuscaPorIdUseCaseImpl;
import br.com.on.fiap.core.application.usecase.cliente.impl.ClienteInsereUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteUseCaseBeanConfig {

    private final ClienteGateway clienteGateway;

    public ClienteUseCaseBeanConfig(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
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
    public ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase() {
        return new ClienteBuscaPorIdUseCaseImpl(clienteGateway);
    }
}
