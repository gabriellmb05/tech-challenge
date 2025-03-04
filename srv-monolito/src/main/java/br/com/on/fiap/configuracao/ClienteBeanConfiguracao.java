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
