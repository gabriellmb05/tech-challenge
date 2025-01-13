package br.com.on.fiap.casosdeuso;

import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.portas.saida.PersisteProdutoPortaSaida;
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
