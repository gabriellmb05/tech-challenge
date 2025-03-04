package br.com.on.fiap.adapter.output.persistence.mapper;

import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import java.util.List;

import br.com.on.fiap.core.domain.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoSaidaMapeador {

    List<Produto> paraListaProdutos(List<ProdutoEntity> produtoEntities);

    @Mapping(target = "id", source = "proId")
    @Mapping(target = "nome", source = "nmNome")
    @Mapping(target = "categoria", source = "tpCategoria")
    @Mapping(target = "preco", source = "vlProduto")
    Produto paraProduto(ProdutoEntity produtoEntity);

    @Mapping(target = "proId", source = "id")
    @Mapping(target = "nmNome", source = "nome")
    @Mapping(target = "tpCategoria", source = "categoria")
    @Mapping(target = "vlProduto", source = "preco")
    ProdutoEntity paraEntidade(Produto produto);
}
