package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoQuantidadeSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
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
