package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.hexagono.dominio.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {PedidoProdutoSaidaMapeador.class})
public interface PedidoSaidaMapeador {

    @Mapping(target = "produtos", ignore = true)
    @Mapping(target = "dataHora", expression = "java(java.time.LocalDateTime.now())")
    PedidoEntidade paraEntidade(Pedido pedido);

    @Mapping(target = "relPedidoProdutos", source = "produtos")
    Pedido paraPedido(PedidoEntidade pedidoEntidade);
}
