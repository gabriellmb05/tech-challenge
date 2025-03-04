package br.com.on.fiap.adapter.output.persistence.repository;

import br.com.on.fiap.adapter.output.persistence.entity.PedidoProdutoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.rel.RelPedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity, RelPedId> {}
