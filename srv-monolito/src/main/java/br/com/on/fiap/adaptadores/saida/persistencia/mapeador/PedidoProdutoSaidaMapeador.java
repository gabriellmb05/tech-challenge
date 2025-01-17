package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.relacionamento.RelPedId;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PedidoProdutoSaidaMapeador {

    List<PedidoProdutoEntidade> paraListaEntidade(List<RelPedidoProduto> relPedidoProdutos);

    List<RelPedidoProduto> paraRelPedidoProduto(List<PedidoProdutoEntidade> relPedidoProdutos);

    @AfterMapping
    default void mapearPedidoProdutoId(
            @MappingTarget PedidoProdutoEntidade pedidoProdutoEntidade, RelPedidoProduto relPedidoProduto) {
        RelPedId id = new RelPedId(
                relPedidoProduto.getPedido().getId(),
                relPedidoProduto.getProduto().getId());
        pedidoProdutoEntidade.setId(id);
    }
}
