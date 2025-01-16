package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;

import java.util.List;

public class InserePedidoCasoDeUso implements InserePedidoPortaEntrada {

	private final PersisteClientePortaSaida persisteClientePortaSaida;
	private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;
	private final PersistePedidoPortaSaida persistePedidoPortaSaida;

	public InserePedidoCasoDeUso(PersisteClientePortaSaida persisteClientePortaSaida,
			PersisteProdutoPortaSaida persisteProdutoPortaSaida, PersistePedidoPortaSaida persistePedidoPortaSaida) {
		this.persisteClientePortaSaida = persisteClientePortaSaida;
		this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
		this.persistePedidoPortaSaida = persistePedidoPortaSaida;
	}

	@Override
	public Pedido inserir(Pedido pedido) {
		persisteClientePortaSaida.buscaClientePorId(pedido.getCliente().getId()).orElseThrow(
				() -> new ClienteNaoEncontradoExcecao(MessageError.MSG_CLIENTE_NAO_ENCONTRATO_PARA_ID.getMensagem(),
						pedido.getCliente().getId()));

		List<Long> idsProdutos = pedido.getProdutos().stream().map(RelPedidoProduto::getProduto).map(Produto::getId)
				.toList();
		persisteProdutoPortaSaida.buscaProdutoPorIdsLista(idsProdutos).ifPresent(p -> {
			if (p.size() != idsProdutos.size()) {
				Object[] idsFaltantes = idsProdutos.stream().filter(id -> !p.contains(id)).toArray();
				throw new ProdutoNaoEncontradoExcecao(MessageError.MSG_PRODUTOS_NAO_ENCONTRADOS.getMensagem(),
						idsFaltantes);
			}
		});

		return persistePedidoPortaSaida.salvaPedido(pedido);
	}
}
