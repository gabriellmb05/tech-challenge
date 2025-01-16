package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PedidoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoEntradaMapeador {

	PedidoRespostaDTO paraPedidoDTO(Pedido pedido);

	Pedido paraPedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO);

}
