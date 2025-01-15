package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoFiltroDTO;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProdutoFiltroMapeador {

	@Mapping(target = "categoria", qualifiedByName = "getCategoria")
	ProdutoFiltro paraProdutoFiltro(ProdutoFiltroDTO produtoFiltroDTO);

	@Named("getCategoria")
	default Categoria getCategoria(String categoria) {
		if (categoria == null) {
			return null;
		}
		return Categoria.buscaCategoria(categoria);
	}
}
