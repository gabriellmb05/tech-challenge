package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ClienteSaidaMapeador.class})
public interface PedidoSaidaMapeador {

    @Mapping(target = "relPedPro", ignore = true)
    @Mapping(target = "cliId", source = "cliente")
    @Mapping(target = "stPedido", expression = "java(br.com.on.fiap.hexagono.dominio.SituacaoPedido.REALIZADO)")
    @Mapping(target = "vlPedido", source = "valor")
    @Mapping(target = "dhPedido", expression = "java(java.time.LocalDateTime.now())")
    PedidoEntidade paraEntidade(Pedido pedido);

    @Mapping(target = "relPedidoProdutos", ignore = true)
    @Mapping(target = "cliente", source = "cliId")
    @Mapping(target = "situacao", source = "stPedido")
    @Mapping(target = "valor", source = "vlPedido")
    @Mapping(target = "dataHora", source = "dhPedido")
    Pedido paraPedido(PedidoEntidade pedidoEntidade);
}
