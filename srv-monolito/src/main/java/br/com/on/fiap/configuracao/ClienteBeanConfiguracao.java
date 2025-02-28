package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.interfaceadapters.apresentadores.ClienteApresentadorImpl;
import br.com.on.fiap.hexagono.interfaceadapters.controladores.ClienteControladorImpl;
import br.com.on.fiap.hexagono.interfaceadapters.gateways.ClienteGatewayImpl;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller.ClienteControlador;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.datasource.ClienteDataSource;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.ClienteApresentador;
import br.com.on.fiap.hexagono.usecases.casodeuso.cliente.BuscaClienteCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.casodeuso.cliente.InsereClienteCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente.InsereClienteCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente.ClienteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfiguracao {

    @Bean
    public BuscaClientePorCpfCasoDeUso buscaCliente(ClienteGateway clienteGateway) {
        return new BuscaClienteCasoDeUsoImpl(clienteGateway);
    }

    @Bean
    public InsereClienteCasoDeUso insereCliente(ClienteGateway clienteGateway) {
        return new InsereClienteCasoDeUsoImpl(clienteGateway);
    }

    @Bean
    public ClienteGateway persisteClienteGateway(ClienteDataSource clienteDataSource) {
        return new ClienteGatewayImpl(clienteDataSource);
    }

    @Bean
    public ClienteApresentador clienteApresentador() {
        return new ClienteApresentadorImpl();
    }

    @Bean
    public ClienteControlador clienteControlador(
            InsereClienteCasoDeUso insereClienteCasoDeUso,
            BuscaClientePorCpfCasoDeUso buscaClientePorCpfCasoDeUso,
            ClienteApresentador clienteApresentador) {
        return new ClienteControladorImpl(insereClienteCasoDeUso, buscaClientePorCpfCasoDeUso, clienteApresentador);
    }
}
