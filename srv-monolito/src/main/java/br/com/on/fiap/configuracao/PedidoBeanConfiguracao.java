package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.produto.ValidaProdutosCasoDeUso;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguracao {

	@Bean
	public InserePedidoCasoDeUso inserePedido(PersisteClientePortaSaida persisteClientePortaSaida,
			PersistePedidoPortaSaida persistePedidoPortaSaida, ValidaProdutosCasoDeUso validaProdutosCasoDeUso) {
		return new InserePedidoCasoDeUso(persisteClientePortaSaida, persistePedidoPortaSaida, validaProdutosCasoDeUso);
	}

}
