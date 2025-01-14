package br.com.on.fiap.casosdeuso;

import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.portas.entrada.InsereProdutoPortaEntrada;
import br.com.on.fiap.portas.saida.PersisteProdutoPortaSaida;
import java.util.Optional;

public class InsereProdutoCasoDeUso implements InsereProdutoPortaEntrada {

  private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

  public InsereProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
    this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
  }

  @Override
  public Produto inserir(Produto produto) {
    Optional<Produto> produtoBancoDados =
        persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome());
    produtoBancoDados.ifPresent(
        p -> {
          throw new ProdutoExistenteExcecao(p.getNome());
        });
    return persisteProdutoPortaSaida.salvaProduto(produto);
  }
}
