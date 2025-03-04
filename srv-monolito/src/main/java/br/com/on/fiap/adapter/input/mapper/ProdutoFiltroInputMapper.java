package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroDTO;
import java.util.Objects;

import br.com.on.fiap.core.domain.entity.Categoria;
import br.com.on.fiap.core.domain.entity.ProdutoFiltro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProdutoFiltroInputMapper {

    @Mapping(target = "categoria", source = "categoria", qualifiedByName = "mapearCategoria")
    ProdutoFiltro paraProdutoFiltro(ProdutoFiltroDTO produtoFiltroDTO);

    @Named("mapearCategoria")
    default Categoria mapearCategoria(String categoria) {
        return Objects.nonNull(categoria) ? Categoria.deString(categoria) : null;
    }
}
