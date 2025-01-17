package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import br.com.on.fiap.hexagono.portas.saida.pedido.ListarPedidosPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListagemPedidoAdaptador implements ListarPedidosPortaSaida {

	private final PedidoRepositorio pedidoRepositorio;
	private final PedidoSaidaMapeador pedidoSaidaMapeador;

	public ListagemPedidoAdaptador(PedidoRepositorio pedidoRepositorio, PedidoSaidaMapeador pedidoSaidaMapeador) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.pedidoSaidaMapeador = pedidoSaidaMapeador;
	}

	@Override
	public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
		return pedidoRepositorio.buscarPedidosPorFiltro(filtro, page).map(pedidoSaidaMapeador::paraPedido);
	}
}
