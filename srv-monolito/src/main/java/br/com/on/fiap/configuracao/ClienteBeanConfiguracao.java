package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.cliente.BuscaClienteCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.cliente.InsereClienteCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.cliente.BuscaClientePorCpfPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.cliente.InsereClientePortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfiguracao {

	@Bean
	public BuscaClientePorCpfPortaEntrada buscaCliente(PersisteClientePortaSaida persisteClientePortaSaida) {
		return new BuscaClienteCasoDeUso(persisteClientePortaSaida);
	}

	@Bean
	public InsereClientePortaEntrada insereCliente(PersisteClientePortaSaida persisteClientePortaSaida) {
		return new InsereClienteCasoDeUso(persisteClientePortaSaida);
	}
}
