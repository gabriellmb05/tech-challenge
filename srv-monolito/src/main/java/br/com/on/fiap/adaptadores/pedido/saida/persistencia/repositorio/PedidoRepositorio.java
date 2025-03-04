package br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio;

import br.com.on.fiap.entidades.PedidoEntidade;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio
        extends JpaRepository<PedidoEntidade, Long>, JpaSpecificationExecutor<PedidoEntidade> {

    @Query(
            value =
                    """
            SELECT
                p
            FROM
                PedidoEntidade p
            LEFT JOIN FETCH p.relPedPro
            LEFT JOIN FETCH p.cliId
            LEFT JOIN FETCH p.pagId
            WHERE
                p.nmProtocolo = :protocolo
            """)
    Optional<PedidoEntidade> findByNmProtocolo(@Param("protocolo") String protocolo);
}
