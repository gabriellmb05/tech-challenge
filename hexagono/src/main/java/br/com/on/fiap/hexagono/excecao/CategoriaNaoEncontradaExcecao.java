package br.com.on.fiap.excecao;

import java.text.MessageFormat;

public class CategoriaNaoEncontradaExcecao extends RuntimeException {

  public CategoriaNaoEncontradaExcecao(String categoria) {
    super(MessageFormat.format("Categoria ({0}) não encontrada", categoria));
  }
}
