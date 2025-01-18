package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ClienteSaidaMapeador.class, ProdutoSaidaMapeador.class})
public interface PedidoProdutoSaidaMapeador {

    @Mapping(target = "relPedPro", ignore = true)
    @Mapping(target = "cliId", source = "cliente")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.hexagono.dominio.SituacaoPedido.REALIZADO)")
    @Mapping(target = "vlPedido", source = "valor")
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    PedidoEntidade paraEntidade(Pedido pedido);

    @Mapping(target = "id", source = "pedId")
    @Mapping(target = "relPedidoProdutos", source = "relPedPro")
    @Mapping(target = "cliente", source = "cliId")
    @Mapping(target = "situacao", source = "stPedido")
    @Mapping(target = "protocolo", source = "nmProtocolo")
    @Mapping(target = "valor", source = "vlPedido")
    @Mapping(target = "dataHora", source = "dhPedido")
    Pedido paraPedido(PedidoEntidade pedidoEntidade);

    List<PedidoProdutoEntidade> paraListaEntidade(List<RelPedidoProduto> relPedidoProdutos);

    List<RelPedidoProduto> paraListaRelPedidoProduto(List<PedidoProdutoEntidade> relPedidoProdutos);

    @Mapping(target = "id.pedId", source = "pedido.id")
    @Mapping(target = "id.proId", source = "produto.id")
    @Mapping(target = "proId", source = "produto")
    @Mapping(target = "qtPedido", source = "quantidade")
    PedidoProdutoEntidade paraEntidade(RelPedidoProduto relPedidoProdutos);

    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "produto", source = "proId")
    @Mapping(target = "quantidade", source = "qtPedido")
    RelPedidoProduto paraRelPedidoProduto(PedidoProdutoEntidade pedidoProdutoEntidade);
}
