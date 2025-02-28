package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PagamentoEntidade;
import br.com.on.fiap.hexagono.entities.entidades.Pagamento;
import br.com.on.fiap.hexagono.entities.entidades.SituacaoPagamento;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PagamentoSaidaMapeador {

    Pagamento paraPagamento(PagamentoEntidade pagamentoEntidade);

    PagamentoEntidade paraEntidade(Pagamento pagamento);

    @Mapping(target = "stPagamento", source = "stPagamento", qualifiedByName = "definirStPagamentoAprovado")
    @Mapping(target = "dhPagamento", source = "dhPagamento", qualifiedByName = "definirDtHoraPagamento")
    PagamentoEntidade paraEntidadeComPagamentoAprovado(Pagamento pagamento);

    @Named("definirStPagamentoAprovado")
    default SituacaoPagamento mapRelPedidoProduto(SituacaoPagamento situacaoPagamento) {
        return SituacaoPagamento.APROVADO;
    }

    @Named("definirDtHoraPagamento")
    default LocalDateTime mapRelPedidoProduto(LocalDateTime dhPagamento) {
        return LocalDateTime.now();
    }
}
