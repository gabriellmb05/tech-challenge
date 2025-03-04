package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.core.domain.model.ProdutoRespostaDTO;
import br.com.on.fiap.core.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoInputMapper {

    ProdutoInputMapper INSTANCE = Mappers.getMapper(ProdutoInputMapper.class);

    ProdutoRespostaDTO paraProdutoDTO(Produto produto);
}
