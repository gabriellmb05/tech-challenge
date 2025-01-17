package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.produto.ValidaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidaProdutosCasoDeUso implements ValidaProdutoPortaEntrada {

	private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

	public ValidaProdutosCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
		this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
	}

	@Override
	public void validar(List<RelPedidoProduto> produtos) {
		List<Long> idsProdutos = extrairIdsProdutos(produtos);
		Set<Long> produtosExistentes = buscarProdutosExistentes(idsProdutos);

		validarSeTodosProdutosExistem(idsProdutos, produtosExistentes);
	}

	private List<Long> extrairIdsProdutos(List<RelPedidoProduto> produtos) {
		return produtos.stream().map(RelPedidoProduto::getProduto).map(Produto::getId).toList();
	}

	private Set<Long> buscarProdutosExistentes(List<Long> idsProdutos) {
		return persisteProdutoPortaSaida.buscaProdutoPorIdsLista(idsProdutos)
				.orElseThrow(() -> new ProdutoNaoEncontradoExcecao(
						MessageError.MSG_PRODUTOS_NAO_ENCONTRADOS.getMensagem(), idsProdutos.toArray()))
				.stream().map(Produto::getId).collect(Collectors.toSet());
	}

	private void validarSeTodosProdutosExistem(List<Long> idsProdutos, Set<Long> produtosExistentes) {
		List<Long> idsFaltantes = idsProdutos.stream().filter(id -> !produtosExistentes.contains(id)).toList();

		if (!idsFaltantes.isEmpty()) {
			throw new ProdutoNaoEncontradoExcecao(MessageError.MSG_PRODUTOS_NAO_ENCONTRADOS.getMensagem(),
					idsFaltantes.toArray());
		}
	}
}
