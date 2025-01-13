package br.com.on.fiap.hexagono.portas.saida;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PersisteProdutoPortaSaida {

    public Optional<Produto> buscaProdutoPorId(Long id);
    public Produto salvaProduto(Produto produto);
    public Optional<Produto> buscaProdutoPorNome(String nome);
    public void deletaProdutoPorId(Long id);
    public Page<Produto> listarTodosProdutos(Pageable page);
    public Page<Produto> listarProdutosPorCategoria(Categoria categoria, Pageable page);
}
