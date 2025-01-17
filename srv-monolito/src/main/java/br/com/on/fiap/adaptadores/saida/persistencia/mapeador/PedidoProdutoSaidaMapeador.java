package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoId;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoProdutoSaidaMapeador {

	List<PedidoProdutoEntidade> paraListaEntidade(List<RelPedidoProduto> relPedidoProdutos);

	@AfterMapping
	default void mapearPedidoProdutoId(@MappingTarget PedidoProdutoEntidade pedidoProdutoEntidade,
			RelPedidoProduto relPedidoProduto) {
		PedidoProdutoId id = new PedidoProdutoId(relPedidoProduto.getPedido().getId(),
				relPedidoProduto.getProduto().getId());
		pedidoProdutoEntidade.setId(id);
	}
}
