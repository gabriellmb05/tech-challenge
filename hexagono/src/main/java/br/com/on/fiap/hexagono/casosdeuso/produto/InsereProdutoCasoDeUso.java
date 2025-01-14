package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.portas.entrada.produto.InsereProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.util.Optional;

public class InsereProdutoCasoDeUso implements InsereProdutoPortaEntrada {

	private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

	public InsereProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
		this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
	}

	@Override
	public Produto inserir(Produto produto) {
		Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome());
		produtoBancoDados.ifPresent(p -> {
			throw new ProdutoExistenteExcecao(p.getNome());
		});
		return persisteProdutoPortaSaida.salvaProduto(produto);
	}
}
