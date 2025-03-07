package br.com.on.fiap.adapter.output.persistence.component;

import br.com.on.fiap.core.application.dto.resposta.paginacao.Direcao;
import br.com.on.fiap.core.application.dto.resposta.paginacao.OrdenacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
public class PageableComponent {

    public Pageable criarPageable(PaginacaoResposta paginacaoResposta) {
        PageRequest pageRequest = PageRequest.of(paginacaoResposta.getPagina(), paginacaoResposta.getTamanhoPagina());

        if (paginacaoResposta.getOrdenacaoResposta() != null) {
            Sort sort = criarSort(paginacaoResposta.getOrdenacaoResposta());
            pageRequest = pageRequest.withSort(sort);
        }

        return pageRequest;
    }

    private Sort criarSort(OrdenacaoResposta ordenacaoResposta) {
        return Sort.by(criarOrdem(ordenacaoResposta));
    }

    private Order criarOrdem(OrdenacaoResposta ordenacaoResposta) {
        return new Order(criarDirecao(ordenacaoResposta.getDirecao()), ordenacaoResposta.getCampo());
    }

    private Direction criarDirecao(Direcao direcao) {
        return Direction.fromString(direcao.getValor());
    }
}
