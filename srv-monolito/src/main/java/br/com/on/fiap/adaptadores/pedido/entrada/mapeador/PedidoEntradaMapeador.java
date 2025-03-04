package br.com.on.fiap.adaptadores.pedido.entrada.mapeador;

import br.com.on.fiap.adaptadores.cliente.entrada.mapeador.ClienteEntradaMapeador;
import br.com.on.fiap.adaptadores.pagamento.entrada.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.solicitacao.PedidoQuantidadeSolicitacaoDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.produto.entrada.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.RelPedidoProduto;
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
