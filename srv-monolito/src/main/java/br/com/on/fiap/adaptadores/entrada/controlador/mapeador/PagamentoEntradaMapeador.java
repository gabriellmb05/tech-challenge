package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PagamentoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.TipoPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PagamentoEntradaMapeador {

    @Mapping(target = "id", source = "pagId")
    PagamentoRespostaDTO paraPagamentoDTO(Pagamento pagamento);

    @Mapping(target = "tpPagamento", source = "tpPagamento", qualifiedByName = "definirTpPagamento")
    @Mapping(target = "stPagamento", expression = "java(br.com.on.fiap.hexagono.dominio.SituacaoPagamento.PENDENTE)")
    Pagamento paraPagamento(PagamentoSolicitacaoDTO pagamentoSolicitacaoDTO);

    default Pagamento map(Long id) {
        return id == null ? null : new Pagamento(id);
    }

    @Named("definirTpPagamento")
    default TipoPagamento mapRelPedidoProduto(Integer tpPagamento) {
        return TipoPagamento.deCodigo(tpPagamento);
    }
}
