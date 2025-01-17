package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoProdutoRepositorio;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoProdutoPortaSaida;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersistenciaPedidoProdutoAdaptador implements PersistePedidoProdutoPortaSaida {

	private final PedidoProdutoRepositorio pedidoProdutoRepositorio;
	private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

	public PersistenciaPedidoProdutoAdaptador(PedidoProdutoRepositorio pedidoProdutoRepositorio,
			PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
		this.pedidoProdutoRepositorio = pedidoProdutoRepositorio;
		this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
	}

	@Override
	public void vincularPedido(List<RelPedidoProduto> relPedidoProdutos) {
		List<PedidoProdutoEntidade> pedidoProdutos = pedidoProdutoSaidaMapeador.paraListaEntidade(relPedidoProdutos);
		pedidoProdutoRepositorio.saveAll(pedidoProdutos);
	}
}
