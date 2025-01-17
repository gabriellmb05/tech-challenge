package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<PedidoEntidade, Long> {

	@Query("""
			SELECT p FROM Pedido p
			WHERE (:#{#filtro.valorMinimo} IS NULL OR p.valor >= :#{#filtro.valorMinimo})
			AND (:#{#filtro.valorMaximo} IS NULL OR p.valor <= :#{#filtro.valorMaximo})
			AND (:#{#filtro.situacao} IS NULL OR p.situacao = :#{#filtro.situacao})
			""")
	Page<PedidoEntidade> buscarPedidosPorFiltro(PedidoFiltro filtro, Pageable pageable);
}
