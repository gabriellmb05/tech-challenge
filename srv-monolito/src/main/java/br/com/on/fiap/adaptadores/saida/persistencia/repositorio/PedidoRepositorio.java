package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepositorio extends JpaRepository<PedidoEntidade, Long> {

	Optional<PedidoEntidade> findByProtocolo(String protocolo);

	@Query("""
			SELECT
			    p
			FROM
			    PedidoEntidade p
			JOIN p.cliente c
			WHERE
			    (
			        :#{#filtro.situacao} IS NULL
			            OR p.situacao = :#{#filtro.situacao}
			    )
			    AND (
			        :#{#filtro.cpfCliente} IS NULL
			            OR c.cpf = :#{#filtro.cpfCliente}
			    )
			    AND (
			        :#{#filtro.dataInicio} IS NULL
			            OR DATE_TRUNC(
			                'day',
			                p.dataHora
			            ) >= :#{#filtro.dataInicio}
			    )
			    AND (
			        :#{#filtro.dataFim} IS NULL
			            OR DATE_TRUNC(
			                'day',
			                p.dataHora
			            ) <= :#{#filtro.dataFim}
			    )
			""")
	Page<PedidoEntidade> buscarPedidosPorFiltro(@Param(value = "filtro") PedidoFiltro filtro, Pageable pageable);
}
