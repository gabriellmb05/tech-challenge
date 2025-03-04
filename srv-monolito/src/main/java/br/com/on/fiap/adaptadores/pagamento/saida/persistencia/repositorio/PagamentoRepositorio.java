package br.com.on.fiap.adaptadores.pagamento.saida.persistencia.repositorio;

import br.com.on.fiap.entidade.PagamentoEntidade;
import br.com.on.fiap.entidade.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositorio
        extends JpaRepository<PagamentoEntidade, Long>, JpaSpecificationExecutor<PedidoEntidade> {}
