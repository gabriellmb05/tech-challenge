package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.relacionamento.RelPedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepositorio extends JpaRepository<PedidoProdutoEntidade, RelPedId> {}
