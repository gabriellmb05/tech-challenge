package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguracao {

	@Bean
	public InserePedidoCasoDeUso inserePedido(PersisteClientePortaSaida persisteClientePortaSaida,
			PersisteProdutoPortaSaida persisteProdutoPortaSaida, PersistePedidoPortaSaida persistePedidoPortaSaida) {
		return new InserePedidoCasoDeUso(persisteClientePortaSaida, persisteProdutoPortaSaida,
				persistePedidoPortaSaida);
	}

}
