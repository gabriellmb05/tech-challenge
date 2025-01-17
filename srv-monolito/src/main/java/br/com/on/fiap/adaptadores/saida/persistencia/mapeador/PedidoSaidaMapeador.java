package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {PedidoProdutoSaidaMapeador.class})
public interface PedidoSaidaMapeador {

    @Mapping(target = "relPedPro", ignore = true)
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.hexagono.dominio.SituacaoPedido.REALIZADO)")
    PedidoEntidade paraEntidade(Pedido pedido);

    @Mapping(target = "relPedidoProdutos", source = "relPedPro")
    Pedido paraPedido(PedidoEntidade pedidoEntidade);
}
