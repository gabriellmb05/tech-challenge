package br.com.on.fiap.adaptadores.entrada.manipulador.mapeador;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.PedidoQuantidadeSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.entities.entidades.RelPedidoProduto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring",
        uses = {ClienteEntradaMapeador.class, ProdutoEntradaMapeador.class, PagamentoEntradaMapeador.class})
public interface PedidoEntradaMapeador {

    PedidoRespostaDTO paraPedidoDTO(Pedido pedido);

    @Mapping(target = "produtos", expression = "java(mapearProdutos(pedido.getRelPedidoProdutos()))")
    @Mapping(target = "pagamento", source = "pagamento")
    PedidoDetalhadoRespostaDTO paraPedidoDetalheDTO(Pedido pedido);

    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "pagamento", source = "pagamento")
    @Mapping(target = "relPedidoProdutos", source = "produtos", qualifiedByName = "mapearRelPedidoProduto")
    Pedido paraPedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO);

    @Mapping(target = "produto", source = "idProduto", qualifiedByName = "mapearProduto")
    RelPedidoProduto paraRelPedidoProduto(PedidoQuantidadeSolicitacaoDTO produtoDTO);

    @Named("mapearProduto")
    default Produto mapearProduto(Long id) {
        return id == null ? null : new Produto(id);
    }

    @Named("mapearRelPedidoProduto")
    default List<RelPedidoProduto> mapearRelPedidoProduto(List<PedidoQuantidadeSolicitacaoDTO> produtos) {
        return produtos.stream().map(this::paraRelPedidoProduto).toList();
    }

    default List<ProdutoRespostaDTO> mapearProdutos(List<RelPedidoProduto> relPedidoProdutos) {
        return relPedidoProdutos.stream()
                .map(RelPedidoProduto::getProduto)
                .map(ProdutoEntradaMapeador.INSTANCE::paraProdutoDTO)
                .toList();
    }
}
