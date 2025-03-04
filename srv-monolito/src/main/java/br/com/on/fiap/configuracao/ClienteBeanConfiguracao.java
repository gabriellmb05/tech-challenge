package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.ClienteApresentador;
import br.com.on.fiap.hexagono.adaptadores.apresentadores.impl.ClienteApresentadorImpl;
import br.com.on.fiap.hexagono.adaptadores.controladores.ClienteControlador;
import br.com.on.fiap.hexagono.adaptadores.controladores.impl.ClienteControladorImpl;
import br.com.on.fiap.hexagono.adaptadores.datasource.ClienteDataSource;
import br.com.on.fiap.hexagono.adaptadores.gateways.ClienteGateway;
import br.com.on.fiap.hexagono.adaptadores.gateways.impl.ClienteGatewayImpl;
import br.com.on.fiap.hexagono.casodeuso.cliente.BuscaClienteCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.cliente.InsereClienteCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.InsereClienteCasoDeUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ClienteBeanConfiguracao {

    private final ClienteGateway clienteGateway;
    private final ClienteDataSource clienteDataSource;
    private final ClienteApresentador clienteApresentador;

    @Lazy
    public ClienteBeanConfiguracao(
            ClienteGateway clienteGateway,
            ClienteDataSource clienteDataSource,
            ClienteApresentador clienteApresentador) {
        this.clienteGateway = clienteGateway;
        this.clienteDataSource = clienteDataSource;
        this.clienteApresentador = clienteApresentador;
    }

    @Bean
    public BuscaClientePorCpfCasoDeUso buscaCliente() {
        return new BuscaClienteCasoDeUsoImpl(clienteGateway);
    }

    @Bean
    public InsereClienteCasoDeUso insereCliente() {
        return new InsereClienteCasoDeUsoImpl(clienteGateway);
    }

    @Bean
    public ClienteGateway persisteClienteGateway() {
        return new ClienteGatewayImpl(clienteDataSource);
    }

    @Bean
    public ClienteApresentador clienteApresentador() {
        return new ClienteApresentadorImpl();
    }

    @Bean
    public ClienteControlador clienteControlador() {
        return new ClienteControladorImpl(insereCliente(), buscaCliente(), clienteApresentador);
    }
}
