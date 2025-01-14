package br.com.on.fiap.excecao;

import java.text.MessageFormat;

public class ProdutoNaoEncontratoExcecao extends RuntimeException {

  public ProdutoNaoEncontratoExcecao(Long id) {
    super(MessageFormat.format("Produto ({0}) não encontrado", id));
  }
}
