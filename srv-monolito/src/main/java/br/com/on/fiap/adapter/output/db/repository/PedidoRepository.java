package br.com.on.fiap.adapter.output.db.repository;

import br.com.on.fiap.adapter.output.db.entity.PedidoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>, JpaSpecificationExecutor<PedidoEntity> {

    @Query(
            value =
                    """
                            SELECT
                                p
                            FROM
                                PedidoEntity p
                            LEFT JOIN FETCH p.relPedPro
                            LEFT JOIN FETCH p.cliId
                            LEFT JOIN FETCH p.pagId
                            WHERE
                                p.nmProtocolo = :protocolo
                            """)
    Optional<PedidoEntity> findByNmProtocolo(@Param("protocolo") String protocolo);
}
