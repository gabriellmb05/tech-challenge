package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepositorio
        extends JpaRepository<PedidoEntidade, Long>, JpaSpecificationExecutor<PedidoEntidade> {

    Optional<PedidoEntidade> findByNmProtocolo(String protocolo);
}
