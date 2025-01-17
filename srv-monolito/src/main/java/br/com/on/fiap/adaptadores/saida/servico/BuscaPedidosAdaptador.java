package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.especificacao.PedidoEspecificacao;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import br.com.on.fiap.hexagono.portas.saida.pedido.BuscaPedidosPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscaPedidosAdaptador implements BuscaPedidosPortaSaida {

	private final PedidoRepositorio pedidoRepositorio;
	private final PedidoSaidaMapeador pedidoSaidaMapeador;

	public BuscaPedidosAdaptador(PedidoRepositorio pedidoRepositorio, PedidoSaidaMapeador pedidoSaidaMapeador) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.pedidoSaidaMapeador = pedidoSaidaMapeador;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
		return pedidoRepositorio.findAll(PedidoEspecificacao.filtroPorDataInicioEDataFim(filtro), page)
				.map(pedidoSaidaMapeador::paraPedido);
	}
}
