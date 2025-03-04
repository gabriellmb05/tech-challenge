package br.com.on.fiap.adaptadores.produto.entrada.mapeador;

import br.com.on.fiap.adaptadores.produto.entrada.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.hexagono.domain.entity.Produto;
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
                    "java(br.com.on.fiap.hexagono.domain.entity.Categoria.deString(produtoSolicitacaoDTO.getCategoria()))")
    Produto paraProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);
}
