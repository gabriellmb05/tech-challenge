package br.com.on.fiap.casosdeuso;

import br.com.on.fiap.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.portas.entrada.DeletaProdutoPortaEntrada;
import br.com.on.fiap.portas.saida.PersisteProdutoPortaSaida;

public class DeletaProdutoCasoDeUso implements DeletaProdutoPortaEntrada {

  private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

  public DeletaProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
    this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
  }

  @Override
  public void deleta(Long id) {
    persisteProdutoPortaSaida
        .buscaProdutoPorId(id)
        .ifPresentOrElse(
            produto -> persisteProdutoPortaSaida.deletaProdutoPorId(id),
            () -> {
              throw new ProdutoNaoEncontratoExcecao(id);
            });
  }
}