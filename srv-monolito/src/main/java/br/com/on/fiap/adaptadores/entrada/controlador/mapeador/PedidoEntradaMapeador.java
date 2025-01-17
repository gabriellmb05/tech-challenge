package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoQuantidadeSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.*;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ProdutoEntradaMapeador.class)
public interface PedidoEntradaMapeador {

    PedidoRespostaDTO paraPedidoDTO(Pedido pedido);

    @Mapping(target = "produtos", source = "relPedidoProdutos", qualifiedByName = "mapProdutosResposta")
    PedidoDetalhadoRespostaDTO paraPedidoDetalheDTO(Pedido pedido);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    default Pedido paraPedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente(pedidoSolicitacaoDTO.getCliente()));
        pedido.setRelPedidoProdutos(produtos(pedidoSolicitacaoDTO.getProdutos(), pedido));
        pedido.setValor(pedidoSolicitacaoDTO.getValor());
        return pedido;
    }

    @Named("mapCliente")
    default Cliente cliente(Long id) {
        return new Cliente(id);
    }

    @Named("mapRelPedidoProduto")
    default List<RelPedidoProduto> produtos(List<PedidoQuantidadeSolicitacaoDTO> produtos, Pedido pedido) {
        return produtos.stream()
                .map(obj -> new RelPedidoProduto(new Produto(obj.getIdProduto()), pedido, obj.getQuantidade()))
                .toList();
    }
}
