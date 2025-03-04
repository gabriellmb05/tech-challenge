package br.com.on.fiap.adaptadores.pedido.entrada.mapeador;

import br.com.on.fiap.adaptadores.pedido.entrada.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.hexagono.domain.entity.PedidoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoFiltroEntradaMapeador {

    PedidoFiltro paraPedidoFiltro(PedidoFiltroDTO pedidoFiltroDTO);
}
