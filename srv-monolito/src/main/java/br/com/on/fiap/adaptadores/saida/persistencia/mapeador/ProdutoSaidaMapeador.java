package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoSaidaMapeador {

    @Mapping(target = "id", source = "proId")
    @Mapping(target = "nome", source = "nmNome")
    @Mapping(target = "categoria", source = "tpCategoria")
    @Mapping(target = "preco", source = "vlProduto")
    Produto paraProduto(ProdutoEntidade produtoEntidade);

    @Mapping(target = "nmNome", source = "nome")
    @Mapping(target = "tpCategoria", source = "categoria")
    @Mapping(target = "vlProduto", source = "preco")
    ProdutoEntidade paraEntidade(Produto produto);
}
