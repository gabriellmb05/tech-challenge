package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long>   {

    public Optional<ProdutoEntidade> findByNome(String nome);
    public List<ProdutoEntidade> findAll();
    public List<ProdutoEntidade> findByCategoria(Categoria categoria);
    public List<ProdutoEntidade> findByNomeContaining(String nome);
}
