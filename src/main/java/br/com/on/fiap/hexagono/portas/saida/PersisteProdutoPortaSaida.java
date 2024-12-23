package br.com.on.fiap.hexagono.portas.saida;

import br.com.on.fiap.hexagono.dominio.Produto;

import java.util.Optional;

public interface PersisteProdutoPortaSaida {

    public Optional<Produto> buscaProdutoPorId(Long id);
    public Produto salvaProduto(Produto produto);
    public Optional<Produto> buscaProdutoPorNome(String nome);

}
