package br.com.on.fiap.adapter.output.persistence.mapper;

import br.com.on.fiap.adapter.output.persistence.entity.PedidoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.PedidoProdutoEntity;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.RelPedidoProduto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ClienteSaidaMapper.class, ProdutoSaidaMapeador.class})
public interface PedidoProdutoSaidaMapeador {

    @Mapping(target = "relPedPro", ignore = true)
    @Mapping(target = "cliId", source = "cliente")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.core.domain.entity.SituacaoPedido.REALIZADO)")
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    PedidoEntity paraEntidade(Pedido pedido);

    @Mapping(target = "id", source = "pedId")
    @Mapping(target = "relPedidoProdutos", source = "relPedPro")
    @Mapping(target = "cliente", source = "cliId")
    @Mapping(target = "situacao", source = "stPedido")
    @Mapping(target = "protocolo", source = "nmProtocolo")
    @Mapping(target = "dataHora", source = "dhPedido")
    Pedido paraPedido(PedidoEntity pedidoEntity);

    @Mapping(target = "id", source = "pedId")
    @Mapping(target = "relPedidoProdutos", source = "relPedPro")
    @Mapping(target = "cliente", source = "cliId")
    @Mapping(target = "situacao", source = "stPedido")
    @Mapping(target = "protocolo", source = "nmProtocolo")
    @Mapping(target = "dataHora", source = "dhPedido")
    @Mapping(target = "pagamento", source = "pagId")
    Pedido paraPedidoComPagamento(PedidoEntity pedidoEntity);

    @Mapping(target = "pedId", source = "id")
    @Mapping(target = "cliId", source = "cliente")
    @Mapping(target = "pagId", source = "pagamento")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.core.domain.entity.SituacaoPedido.REALIZADO)")
    @Mapping(target = "nmProtocolo", source = "protocolo")
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "relPedPro", source = "relPedidoProdutos")
    PedidoEntity paraEntidadeComPagamento(Pedido pedido);

    List<PedidoProdutoEntity> paraListaEntidade(List<RelPedidoProduto> relPedidoProdutos);

    List<RelPedidoProduto> paraListaRelPedidoProduto(List<PedidoProdutoEntity> relPedidoProdutos);

    @Mapping(target = "id.pedId", source = "pedido.id")
    @Mapping(target = "id.proId", source = "produto.id")
    @Mapping(target = "proId", source = "produto")
    @Mapping(target = "qtPedido", source = "quantidade")
    PedidoProdutoEntity paraEntidade(RelPedidoProduto relPedidoProdutos);

    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "produto", source = "proId")
    @Mapping(target = "quantidade", source = "qtPedido")
    RelPedidoProduto paraRelPedidoProduto(PedidoProdutoEntity pedidoProdutoEntity);
}
