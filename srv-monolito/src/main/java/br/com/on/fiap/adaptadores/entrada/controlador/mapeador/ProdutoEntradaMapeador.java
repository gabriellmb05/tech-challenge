package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoEntradaMapeador {

    ProdutoEntradaMapeador INSTANCE = Mappers.getMapper( ProdutoEntradaMapeador.class );

    ProdutoRespostaDTO paraProdutoDTO(Produto produto);

    Produto paraProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);
}
