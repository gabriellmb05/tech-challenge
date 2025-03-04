package br.com.on.fiap.adapter.output.persistence.repository;

import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import br.com.on.fiap.core.domain.entity.ProdutoFiltro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findByNmNome(String nome);

    List<ProdutoEntity> findByProIdIn(List<Long> ids);

    @Query(
            """
                    SELECT
                        p
                    FROM
                        ProdutoEntity p
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
    Page<ProdutoEntity> buscarComFiltro(@Param(value = "filtro") ProdutoFiltro filtro, Pageable page);
}
