package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProdutoRepositorio extends JpaRepository<PedidoProdutoEntidade, PedidoProdutoId> {}
