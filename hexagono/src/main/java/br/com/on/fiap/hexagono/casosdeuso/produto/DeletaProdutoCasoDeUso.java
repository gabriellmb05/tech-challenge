package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.entrada.produto.DeletaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;

public class DeletaProdutoCasoDeUso implements DeletaProdutoPortaEntrada {

	private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

	public DeletaProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
		this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
	}

	@Override
	public void deleta(Long id) {
		persisteProdutoPortaSaida.buscaProdutoPorId(id)
				.ifPresentOrElse(produto -> persisteProdutoPortaSaida.deletaProdutoPorId(id), () -> {
					throw new ProdutoNaoEncontratoExcecao(id);
				});
	}
}
