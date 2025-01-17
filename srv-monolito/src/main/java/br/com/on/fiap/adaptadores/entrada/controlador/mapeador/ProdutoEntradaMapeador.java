package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProdutoEntradaMapeador {

    ProdutoRespostaDTO paraProdutoDTO(Produto produto);

    Produto paraProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);

    @Named("mapProdutosResposta")
    default List<ProdutoRespostaDTO> mapProdutosResposta(List<RelPedidoProduto> relPedidoProdutos) {
        return relPedidoProdutos.stream()
                .map(rel -> new ProdutoRespostaDTO(
                        rel.getProduto().getId(),
                        rel.getProduto().getNome(),
                        rel.getProduto().getCategoria().name(),
                        rel.getProduto().getPreco()))
                .toList();
    }
}
