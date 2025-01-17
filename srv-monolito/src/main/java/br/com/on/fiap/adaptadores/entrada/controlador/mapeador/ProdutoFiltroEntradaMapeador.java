package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.ProdutoFiltroDTO;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProdutoFiltroEntradaMapeador {

    ProdutoFiltro paraProdutoFiltro(ProdutoFiltroDTO produtoFiltroDTO);
}
