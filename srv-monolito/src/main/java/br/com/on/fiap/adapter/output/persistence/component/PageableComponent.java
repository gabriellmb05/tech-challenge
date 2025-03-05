package br.com.on.fiap.adapter.output.persistence.component;

import br.com.on.fiap.core.domain.model.Ordenacao;
import br.com.on.fiap.core.domain.model.Paginacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableComponent {

    public Pageable criarPageable(Paginacao paginacao) {
        PageRequest pageRequest = PageRequest.of(paginacao.getPagina(), paginacao.getTamanhoPagina());

        if (paginacao.getOrdenacao() != null) {
            Sort sort = criarSort(paginacao.getOrdenacao());
            pageRequest = pageRequest.withSort(sort);
        }

        return pageRequest;
    }

    private Sort criarSort(Ordenacao ordenacao) {
        return Sort.by(ordenacao.getDirecao().getValor(), ordenacao.getCampo());
    }
}
