package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProdutoEntradaMapeador {

  @Mapping(target = "categoria", expression = "java(produto.getCategoria().name())")
  ProdutoRespostaDTO paraProdutoDTO(Produto produto);

  @Mapping(target = "categoria", qualifiedByName = "getCategoria")
  Produto paraProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);

  @Named("getCategoria")
  default Categoria getCategoria(String categoria) {
    return Categoria.buscaCategoria(categoria);
  }
}
