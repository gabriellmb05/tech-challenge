package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio
        extends JpaRepository<PedidoEntidade, Long>, JpaSpecificationExecutor<PedidoEntidade> {

    Optional<PedidoEntidade> findByProtocolo(String protocolo);
}
