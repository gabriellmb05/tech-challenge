package br.com.on.fiap.adaptadores.entrada.manipulador.mapeador.filtro;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.hexagono.entities.entidades.PedidoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoFiltroEntradaMapeador {

    PedidoFiltro paraPedidoFiltro(PedidoFiltroDTO pedidoFiltroDTO);
}
