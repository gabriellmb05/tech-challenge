package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoSaidaMapeador {

	Pedido paraPedido(PedidoEntidade pedidoEntidade);

	PedidoEntidade paraEntidade(Pedido pedido);
}
