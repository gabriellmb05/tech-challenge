package br.com.on.fiap.core.application.dto.resposta;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface PaginaResposta<T> {

    List<T> getConteudo();

    Long getTotalElementos();

    Integer getTotalPaginas();

    Integer getTamanhoPagina();

    Integer getPaginaAtual();

    <U> PaginaResposta<U> map(Function<? super T, ? extends U> converter);

    static <T> PaginaResposta<T> create(List<T> conteudo, Long totalElementos, Integer totalPaginas,
                                        Integer tamanhoPagina, Integer paginaAtual) {
        return new PaginaResposta<>() {
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

            @Override
            public <U> PaginaResposta<U> map(Function<? super T, ? extends U> converter) {
                List<U> novoConteudo = conteudo.stream().map(converter).collect(Collectors.toUnmodifiableList());
                return create(novoConteudo, totalElementos, totalPaginas, tamanhoPagina, paginaAtual);
            }
        };
    }
}
