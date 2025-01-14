package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import java.util.stream.Stream;

public enum Categoria {
	LANCHE, ACOMPANHAMENTO, BEBIDA, SOBREMESA;

	public static Categoria buscaCategoria(String categoria) {
		return Stream.of(Categoria.values()).filter(c -> c.name().equals(categoria)).findFirst()
				.orElseThrow(() -> new CategoriaNaoEncontradaExcecao(categoria));
	}
}
