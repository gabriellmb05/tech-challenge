package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
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
        uses = {ClienteEntradaMapeador.class, PagamentoEntradaMapeador.class})
public interface PedidoEntradaMapeador {

    PedidoRespostaDTO paraPedidoDTO(Pedido pedido);

    @Mapping(target = "produtos", source = "relPedidoProdutos")
    PedidoDetalhadoRespostaDTO paraPedidoDetalheDTO(Pedido pedido);

    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "pagamento", source = "pagamento")
    @Mapping(target = "relPedidoProdutos", source = "produtos", qualifiedByName = "mapRelPedidoProduto")
    Pedido paraPedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO);

    @Mapping(target = "produto", source = "idProduto", qualifiedByName = "mapProduto")
    RelPedidoProduto paraRelPedidoProduto(PedidoQuantidadeSolicitacaoDTO produtoDTO);

    @Named("mapProduto")
    default Produto mapProduto(Long id) {
        return id == null ? null : new Produto(id);
    }

    @Named("mapRelPedidoProduto")
    default List<RelPedidoProduto> mapRelPedidoProduto(List<PedidoQuantidadeSolicitacaoDTO> produtos) {
        return produtos.stream().map(this::paraRelPedidoProduto).toList();
    }
}
