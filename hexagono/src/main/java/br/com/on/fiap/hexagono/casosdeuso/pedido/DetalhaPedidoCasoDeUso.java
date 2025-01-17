package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pedido.DetalhaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.pedido.DetalhaPedidoPortaSaida;

public class DetalhaPedidoCasoDeUso implements DetalhaPedidoPortaEntrada {

	private final DetalhaPedidoPortaSaida detalhaPedidoPortaSaida;

	public DetalhaPedidoCasoDeUso(DetalhaPedidoPortaSaida detalhaPedidoPortaSaida) {
		this.detalhaPedidoPortaSaida = detalhaPedidoPortaSaida;
	}

	@Override
	public Pedido detalhaPedido(Long id) {
		return detalhaPedidoPortaSaida.detalhaPedido(id).orElseThrow(
				() -> new PedidoNaoEncontradoExcecao(MessageError.MSG_PEDIDO_NAO_ENCONTRADO_PARA_IO.getMensagem(), id));
	}
}
