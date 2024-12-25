package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.entidade.ProdutoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long> {

    Optional<ProdutoEntidade> findByNome(String nome);
}