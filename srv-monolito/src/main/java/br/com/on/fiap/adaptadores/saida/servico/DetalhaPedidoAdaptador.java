package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.portas.saida.pedido.DetalhaPedidoPortaSaida;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DetalhaPedidoAdaptador implements DetalhaPedidoPortaSaida {

	private final PedidoRepositorio pedidoRepositorio;
	private final PedidoSaidaMapeador pedidoSaidaMapeador;

	public DetalhaPedidoAdaptador(PedidoRepositorio pedidoRepositorio, PedidoSaidaMapeador pedidoSaidaMapeador) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.pedidoSaidaMapeador = pedidoSaidaMapeador;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pedido> detalhaPedido(String protocolo) {
		return pedidoRepositorio.findByProtocolo(protocolo).map(pedidoSaidaMapeador::paraPedido);
	}
}
