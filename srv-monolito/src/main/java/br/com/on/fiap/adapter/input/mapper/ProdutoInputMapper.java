package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.adapter.input.dto.response.ProdutoRespostaDTO;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoDTO;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoInputMapper {

    ProdutoInputMapper INSTANCE = Mappers.getMapper(ProdutoInputMapper.class);

    ProdutoRespostaDTO paraProdutoDTO(Produto produto);

    @Mapping(
            target = "categoria",
            expression =
                    "java(br.com.on.fiap.hexagono.domain.entity.Categoria.deString(produtoSolicitacaoDTO.getCategoria()))")
    Produto paraProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);
}
