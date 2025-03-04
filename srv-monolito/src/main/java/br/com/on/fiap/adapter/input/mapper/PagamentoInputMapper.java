package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.adapter.input.dto.request.PagamentoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.response.PagamentoRespostaDTO;
import br.com.on.fiap.core.domain.entity.Pagamento;
import br.com.on.fiap.core.domain.entity.TipoPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PagamentoInputMapper {

    @Mapping(target = "id", source = "pagId")
    PagamentoRespostaDTO paraPagamentoDTO(Pagamento pagamento);

    @Mapping(target = "tpPagamento", source = "tpPagamento", qualifiedByName = "definirTpPagamento")
    @Mapping(
            target = "stPagamento",
            expression = "java(br.com.on.fiap.core.domain.entity.SituacaoPagamento.PENDENTE)")
    Pagamento paraPagamento(PagamentoSolicitacaoDTO pagamentoSolicitacaoDTO);

    default Pagamento map(Long id) {
        return id == null ? null : new Pagamento(id);
    }

    @Named("definirTpPagamento")
    default TipoPagamento mapRelPedidoProduto(Integer tpPagamento) {
        return TipoPagamento.deCodigo(tpPagamento);
    }
}
