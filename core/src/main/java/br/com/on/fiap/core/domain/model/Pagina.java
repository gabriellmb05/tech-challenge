package br.com.on.fiap.core.domain.model;

import java.util.List;

public interface Pagina<T> {

    List<T> getConteudo();

    Long getTotalElementos();

    Integer getTotalPaginas();

    Integer getTamanhoPagina();

    Integer getPaginaAtual();

    static <T> Pagina<T> create(
            List<T> conteudo, Long totalElementos, Integer totalPaginas, Integer tamanhoPagina, Integer paginaAtual) {
        return new Pagina<>() {
            @Override
            public List<T> getConteudo() {
                return conteudo;
            }

            @Override
            public Long getTotalElementos() {
                return totalElementos;
            }

            @Override
            public Integer getTotalPaginas() {
                return totalPaginas;
            }

            @Override
            public Integer getTamanhoPagina() {
                return tamanhoPagina;
            }

            @Override
            public Integer getPaginaAtual() {
                return paginaAtual;
            }
        };
    }
}
