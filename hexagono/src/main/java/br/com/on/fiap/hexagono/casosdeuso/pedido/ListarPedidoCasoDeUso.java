package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import br.com.on.fiap.hexagono.portas.entrada.pedido.ListarPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.pedido.ListarPedidosPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListarPedidoCasoDeUso implements ListarPedidoPortaEntrada {

	private final ListarPedidosPortaSaida listarPedidoPortaSaida;

	public ListarPedidoCasoDeUso(ListarPedidosPortaSaida listarPedidoPortaSaida) {
		this.listarPedidoPortaSaida = listarPedidoPortaSaida;
	}

	@Override
	public Page<Pedido> listarComFiltro(PedidoFiltro filtro, Pageable pageable) {
		return listarPedidoPortaSaida.listarComFiltros(filtro, pageable);
	}
}
