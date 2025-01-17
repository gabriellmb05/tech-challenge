package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoId;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoProdutoRepositorio;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistenciaPedidoAdaptador implements PersistePedidoPortaSaida {

	private final PedidoRepositorio pedidoRepositorio;
	private final PedidoProdutoRepositorio pedidoProdutoRepositorio;
	private final PedidoSaidaMapeador pedidoSaidaMapeador;

	public PersistenciaPedidoAdaptador(PedidoRepositorio pedidoRepositorio,
			PedidoProdutoRepositorio pedidoProdutoRepositorio, PedidoSaidaMapeador pedidoSaidaMapeador) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.pedidoProdutoRepositorio = pedidoProdutoRepositorio;
		this.pedidoSaidaMapeador = pedidoSaidaMapeador;
	}

	@Override
	public Pedido salvaPedido(Pedido pedido) {
		// Converte o pedido para a entidade de persistência
		PedidoEntidade pedidoEntidade = pedidoSaidaMapeador.paraEntidade(pedido);
		pedidoEntidade.setSituacao(1L);

		PedidoEntidade pedidoPersistido = pedidoRepositorio.save(pedidoEntidade);

		// Agora associamos os produtos ao pedido
		List<PedidoProdutoEntidade> teste = pedido.getProdutos().stream()
				.map(rel -> criarPedidoProduto(pedidoPersistido, new ProdutoEntidade(rel.getProduto().getId()),
						rel.getQuantidade()))
				.toList();

		pedidoProdutoRepositorio.saveAll(teste);
		return pedidoSaidaMapeador.paraPedido(null);
	}

	public PedidoProdutoEntidade criarPedidoProduto(PedidoEntidade pedido, ProdutoEntidade produto, Long quantidade) {
		PedidoProdutoId id = new PedidoProdutoId(pedido.getId(), produto.getId());
		return new PedidoProdutoEntidade(id, pedido, produto, quantidade);
	}
}
