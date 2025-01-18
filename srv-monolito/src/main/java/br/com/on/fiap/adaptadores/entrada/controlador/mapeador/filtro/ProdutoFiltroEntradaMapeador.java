package br.com.on.fiap.adaptadores.entrada.controlador.mapeador.filtro;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.ProdutoFiltroDTO;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoFiltroEntradaMapeador {

    ProdutoFiltro paraProdutoFiltro(ProdutoFiltroDTO produtoFiltroDTO);
}
