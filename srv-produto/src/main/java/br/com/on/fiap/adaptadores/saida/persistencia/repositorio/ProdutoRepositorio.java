package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import java.util.Optional;
import br.com.on.fiap.hexagono.dominio.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long> {

    Optional<ProdutoEntidade> findByNome(String nome);
    List<ProdutoEntidade> findAll();
    List<ProdutoEntidade> findByCategoria(Categoria categoria);
    List<ProdutoEntidade> findByNomeContaining(String nome);
}
