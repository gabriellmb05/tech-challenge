package br.com.on.fiap.adaptadores.entrada.manipulador.mapeador;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.hexagono.entities.entidades.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoEntradaMapeador {

    ProdutoEntradaMapeador INSTANCE = Mappers.getMapper(ProdutoEntradaMapeador.class);

    ProdutoRespostaDTO paraProdutoDTO(Produto produto);

    @Mapping(
            target = "categoria",
            expression =
                    "java(br.com.on.fiap.hexagono.entities.entidades.Categoria.deString(produtoSolicitacaoDTO.getCategoria()))")
    Produto paraProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);
}
