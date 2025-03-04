package br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio;

import br.com.on.fiap.entidades.PedidoProdutoEntidade;
import br.com.on.fiap.entidades.relacionamento.RelPedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepositorio extends JpaRepository<PedidoProdutoEntidade, RelPedId> {}
