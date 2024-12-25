package br.com.on.fiap.hexagono.dominio;

public enum Categoria {
  Lanche,
  Acompanhamento,
  Bebida,
  Sobremesa;

  public static Categoria buscaCategoria(String categoria) {
    return Categoria.valueOf(categoria);
  }
}
