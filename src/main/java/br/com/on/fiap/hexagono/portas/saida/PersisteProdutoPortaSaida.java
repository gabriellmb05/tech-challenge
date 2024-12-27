package br.com.on.fiap.hexagono.portas.saida;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;

import java.util.List;
import java.util.Optional;

public interface PersisteProdutoPortaSaida {

    public Optional<Produto> buscaProdutoPorId(Long id);
    public Produto salvaProduto(Produto produto);
    public Optional<Produto> buscaProdutoPorNome(String nome);
    public void deletaProdutoPorId(Long id);
    public List<Produto> listarTodosProdutos();
    public List<Produto> listarProdutosPorCategoria(Categoria categoria);
}
