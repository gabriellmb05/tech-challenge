package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;

import java.util.List;

public class ValidaProdutosCasoDeUso {

	private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

	public ValidaProdutosCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
		this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
	}

	public void validar(List<RelPedidoProduto> produtos) {
		List<Long> idsProdutos = produtos.stream().map(RelPedidoProduto::getProduto).map(Produto::getId).toList();
		persisteProdutoPortaSaida.buscaProdutoPorIdsLista(idsProdutos).ifPresent(p -> {
			if (p.size() != idsProdutos.size()) {
				Object[] idsFaltantes = idsProdutos.stream().filter(id -> !p.contains(id)).toArray();
				throw new ProdutoNaoEncontradoExcecao(MessageError.MSG_PRODUTOS_NAO_ENCONTRADOS.getMensagem(),
						idsFaltantes);
			}
		});
	}
}
