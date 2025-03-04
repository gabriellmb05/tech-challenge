package br.com.on.fiap.adaptadores.produto.saida.persistencia.repositorio;

import br.com.on.fiap.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long> {

    Optional<ProdutoEntidade> findByNmNome(String nome);

    List<ProdutoEntidade> findByProIdIn(List<Long> ids);

    @Query(
            """
			SELECT
			    p
			FROM
			    ProdutoEntidade p
			WHERE
			    (
			        :#{#filtro.nome} IS NULL
			            OR p.nmNome = :#{#filtro.nome}
			    )
			    AND (
			        :#{#filtro.categoria} IS NULL
			            OR p.tpCategoria = :#{#filtro.categoria}
			    )
			""")
    Page<ProdutoEntidade> buscarComFiltro(@Param(value = "filtro") ProdutoFiltro filtro, Pageable page);
}
