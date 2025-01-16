package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.casosdeuso.produto.ValidaProdutosCasoDeUso;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;

public class InserePedidoCasoDeUso implements InserePedidoPortaEntrada {

	private final PersisteClientePortaSaida persisteClientePortaSaida;
	private final PersistePedidoPortaSaida persistePedidoPortaSaida;
	private final ValidaProdutosCasoDeUso validaProdutosCasoDeUso;

	public InserePedidoCasoDeUso(PersisteClientePortaSaida persisteClientePortaSaida,
			PersistePedidoPortaSaida persistePedidoPortaSaida, ValidaProdutosCasoDeUso validaProdutosCasoDeUso) {
		this.persisteClientePortaSaida = persisteClientePortaSaida;
		this.persistePedidoPortaSaida = persistePedidoPortaSaida;
		this.validaProdutosCasoDeUso = validaProdutosCasoDeUso;
	}

	@Override
	public Pedido inserir(Pedido pedido) {
		persisteClientePortaSaida.buscaClientePorId(pedido.getCliente().getId()).orElseThrow(
				() -> new ClienteNaoEncontradoExcecao(MessageError.MSG_CLIENTE_NAO_ENCONTRATO_PARA_ID.getMensagem(),
						pedido.getCliente().getId()));
		validaProdutosCasoDeUso.validar(pedido.getProdutos());
		return persistePedidoPortaSaida.salvaPedido(pedido);
	}
}
