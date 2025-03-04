package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.adapter.input.dto.filter.PedidoFiltroDTO;
import br.com.on.fiap.hexagono.domain.entity.PedidoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoFiltroInputMapper {

    PedidoFiltro paraPedidoFiltro(PedidoFiltroDTO pedidoFiltroDTO);
}
