package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.relacionamento.RelPedId;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {PedidoSaidaMapeador.class, ProdutoSaidaMapeador.class})
public interface PedidoProdutoSaidaMapeador {

    List<PedidoProdutoEntidade> paraListaEntidade(List<RelPedidoProduto> relPedidoProdutos);

    List<RelPedidoProduto> paraListaRelPedidoProduto(List<PedidoProdutoEntidade> relPedidoProdutos);

    @Mapping(target = "pedId", source = "pedido")
    @Mapping(target = "proId", source = "produto")
    @Mapping(target = "qtPedido", source = "quantidade")
    PedidoProdutoEntidade paraEntidade(RelPedidoProduto relPedidoProdutos);

    @Mapping(target = "pedido", source = "pedId")
    @Mapping(target = "produto", source = "proId")
    @Mapping(target = "quantidade", source = "qtPedido")
    RelPedidoProduto paraRelPedidoProduto(PedidoProdutoEntidade pedidoProdutoEntidade);

    @AfterMapping
    default void mapearPedidoProdutoId(
            @MappingTarget PedidoProdutoEntidade pedidoProdutoEntidade, RelPedidoProduto relPedidoProduto) {
        RelPedId id = new RelPedId(
                relPedidoProduto.getPedido().getId(),
                relPedidoProduto.getProduto().getId());
        pedidoProdutoEntidade.setId(id);
    }
}
