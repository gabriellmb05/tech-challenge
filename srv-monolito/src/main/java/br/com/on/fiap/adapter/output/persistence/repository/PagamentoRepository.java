package br.com.on.fiap.adapter.output.persistence.repository;

import br.com.on.fiap.adapter.output.persistence.entity.PagamentoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository
        extends JpaRepository<PagamentoEntity, Long>, JpaSpecificationExecutor<PedidoEntity> {}
