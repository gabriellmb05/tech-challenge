package br.com.on.fiap.dominio.portas.repositorios;

import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import java.util.List;

public interface IProdutoRepositorioPorta {
    int save(Produto produto);
    int update(Long id, Produto produto);
    Produto findById(Long id);
    int deleteById(Long id);
    List<Produto> findAll();
    List<Produto> findByCategoria(Categoria categoria);
    List<Produto> findByNomeContaining(String nome);
}
