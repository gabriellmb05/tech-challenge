package br.com.on.fiap.adapter.output.persistence.mapper;

import br.com.on.fiap.adapter.output.persistence.entity.PagamentoEntity;
import br.com.on.fiap.core.domain.entity.Pagamento;
import br.com.on.fiap.core.domain.entity.SituacaoPagamento;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PagamentoSaidaMapper {

    Pagamento paraPagamento(PagamentoEntity pagamentoEntity);

    PagamentoEntity paraEntidade(Pagamento pagamento);

    @Mapping(target = "stPagamento", source = "stPagamento", qualifiedByName = "definirStPagamentoAprovado")
    @Mapping(target = "dhPagamento", source = "dhPagamento", qualifiedByName = "definirDtHoraPagamento")
    PagamentoEntity paraEntidadeComPagamentoAprovado(Pagamento pagamento);

    @Named("definirStPagamentoAprovado")
    default SituacaoPagamento mapRelPedidoProduto(SituacaoPagamento situacaoPagamento) {
        return SituacaoPagamento.APROVADO;
    }

    @Named("definirDtHoraPagamento")
    default LocalDateTime mapRelPedidoProduto(LocalDateTime dhPagamento) {
        return LocalDateTime.now();
    }
}
