package br.com.on.fiap.hexagono.dominio;

public enum Categoria {
	LANCHE, ACOMPANHAMENTO, BEBIDA, SOBREMESA;

	public static Categoria buscaCategoria(String categoria) {
		return Categoria.valueOf(categoria);
	}
}
