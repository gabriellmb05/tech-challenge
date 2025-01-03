package br.com.on.fiap.hexagono.portas.saida;

import br.com.on.fiap.hexagono.dominio.Produto;
import java.util.Optional;

public interface PersisteProdutoPortaSaida {

  Optional<Produto> buscaProdutoPorId(Long id);

  Produto salvaProduto(Produto produto);

  Optional<Produto> buscaProdutoPorNome(String nome);

  void deletaProdutoPorId(Long id);
}
