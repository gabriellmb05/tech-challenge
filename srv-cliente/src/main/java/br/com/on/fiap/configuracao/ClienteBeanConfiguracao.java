package br.com.on.fiap.configuracao;

import br.com.on.fiap.adaptadores.saida.servico.PersistenciaClienteAdaptador;
import br.com.on.fiap.hexagono.casosdeuso.cliente.BuscaClienteCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.cliente.InsereClienteCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.cliente.BuscaClientePorCpfPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.cliente.InsereClientePortaEntrada;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfiguracao {

	@Bean
	public BuscaClientePorCpfPortaEntrada buscaCliente(PersistenciaClienteAdaptador persistenciaClienteAdaptador) {
		return new BuscaClienteCasoDeUso(persistenciaClienteAdaptador);
	}

	@Bean
	public InsereClientePortaEntrada insereCliente(PersistenciaClienteAdaptador persistenciaClienteAdaptador) {
		return new InsereClienteCasoDeUso(persistenciaClienteAdaptador);
	}
}
