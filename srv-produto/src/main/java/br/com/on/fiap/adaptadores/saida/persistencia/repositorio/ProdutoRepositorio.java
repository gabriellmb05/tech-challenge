package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long> {

  Optional<ProdutoEntidade> findByNome(String nome);

  @Query(
      """
          SELECT p FROM ProdutoEntidade p
          WHERE (:#{#filtro.nome} IS NULL OR p.nome = :#{#filtro.nome})
          AND (:#{#filtro.categoria} IS NULL OR p.categoria = :#{#filtro.categoria})
      """)
  Page<ProdutoEntidade> buscarComFiltro(
      @Param(value = "filtro") ProdutoFiltro filtro, Pageable page);
}
