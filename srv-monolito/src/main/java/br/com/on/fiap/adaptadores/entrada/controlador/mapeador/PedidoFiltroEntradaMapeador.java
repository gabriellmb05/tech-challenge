package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoFiltroEntradaMapeador {

    PedidoFiltro paraPedidoFiltro(PedidoFiltroDTO pedidoFiltroDTO);
}
