package br.com.on.fiap.portas.saida;

import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersisteProdutoPortaSaida {

  Optional<Produto> buscaProdutoPorId(Long id);

  Produto salvaProduto(Produto produto);

  Optional<Produto> buscaProdutoPorNome(String nome);

  void deletaProdutoPorId(Long id);

  Page<Produto> listarTodosProdutos(Pageable page);

  Page<Produto> listarProdutosPorCategoria(Categoria categoria, Pageable page);
}
