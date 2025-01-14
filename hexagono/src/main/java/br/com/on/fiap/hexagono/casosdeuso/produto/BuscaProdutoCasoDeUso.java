package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.util.Optional;

public class BuscaProdutoCasoDeUso implements BuscaProdutoPorIdPortaEntrada {

	private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

	public BuscaProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
		this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
	}

	@Override
	public Produto buscar(Long id) {
		Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorId(id);
		return produtoBancoDados.orElseThrow(() -> new ProdutoNaoEncontratoExcecao(id));
	}
}
