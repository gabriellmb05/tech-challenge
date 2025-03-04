package br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.cliente.saida.persistencia.mapeador.ClienteSaidaMapeador;
import br.com.on.fiap.adaptadores.produto.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.entidade.PedidoEntidade;
import br.com.on.fiap.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.RelPedidoProduto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ClienteSaidaMapeador.class, ProdutoSaidaMapeador.class})
public interface PedidoProdutoSaidaMapeador {

    @Mapping(target = "relPedPro", ignore = true)
    @Mapping(target = "cliId", source = "cliente")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.hexagono.entidades.SituacaoPedido.REALIZADO)")
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    PedidoEntidade paraEntidade(Pedido pedido);

    @Mapping(target = "id", source = "pedId")
    @Mapping(target = "relPedidoProdutos", source = "relPedPro")
    @Mapping(target = "cliente", source = "cliId")
    @Mapping(target = "situacao", source = "stPedido")
    @Mapping(target = "protocolo", source = "nmProtocolo")
    @Mapping(target = "dataHora", source = "dhPedido")
    Pedido paraPedido(PedidoEntidade pedidoEntidade);

    @Mapping(target = "id", source = "pedId")
    @Mapping(target = "relPedidoProdutos", source = "relPedPro")
    @Mapping(target = "cliente", source = "cliId")
    @Mapping(target = "situacao", source = "stPedido")
    @Mapping(target = "protocolo", source = "nmProtocolo")
    @Mapping(target = "dataHora", source = "dhPedido")
    @Mapping(target = "pagamento", source = "pagId")
    Pedido paraPedidoComPagamento(PedidoEntidade pedidoEntidade);

    @Mapping(target = "pedId", source = "id")
    @Mapping(target = "cliId", source = "cliente")
    @Mapping(target = "pagId", source = "pagamento")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.hexagono.entidades.SituacaoPedido.REALIZADO)")
    @Mapping(target = "nmProtocolo", source = "protocolo")
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "relPedPro", source = "relPedidoProdutos")
    PedidoEntidade paraEntidadeComPagamento(Pedido pedido);

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
