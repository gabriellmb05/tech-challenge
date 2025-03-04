package br.com.on.fiap.adaptadores.pedido.entrada.mapeador;

import br.com.on.fiap.adaptadores.produto.entrada.dto.filtro.ProdutoFiltroDTO;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProdutoFiltroEntradaMapeador {

    @Mapping(target = "categoria", source = "categoria", qualifiedByName = "mapearCategoria")
    ProdutoFiltro paraProdutoFiltro(ProdutoFiltroDTO produtoFiltroDTO);

    @Named("mapearCategoria")
    default Categoria mapearCategoria(String categoria) {
        return Objects.nonNull(categoria) ? Categoria.deString(categoria) : null;
    }
}
