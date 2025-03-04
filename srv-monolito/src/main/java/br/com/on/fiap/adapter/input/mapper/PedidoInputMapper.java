package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.adapter.input.dto.request.PedidoQuantidadeSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.request.PedidoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.response.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adapter.input.dto.response.PedidoRespostaDTO;
import br.com.on.fiap.adapter.input.dto.response.ProdutoRespostaDTO;
import java.util.List;

import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.entity.Produto;
import br.com.on.fiap.core.domain.entity.RelPedidoProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring",
        uses = {ClienteInputMapper.class, ProdutoInputMapper.class, PagamentoInputMapper.class})
public interface PedidoInputMapper {

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
                .map(ProdutoInputMapper.INSTANCE::paraProdutoDTO)
                .toList();
    }
}
