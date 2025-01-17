package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoSaidaMapeador {

	@Mapping(target = "produtos", ignore = true)
	PedidoEntidade paraEntidade(Pedido pedido);

	Pedido paraPedido(PedidoEntidade pedidoEntidade);
}
