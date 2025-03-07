package br.com.on.fiap.core.application.dto.resposta;

public interface Paginacao {
    Integer getPagina();

    Integer getTamanhoPagina();

    Ordenacao getOrdenacao();

    static Paginacao create(Integer pagina, Integer tamanhoPagina, Ordenacao ordenacao) {
        return new Paginacao() {
            @Override
            public Integer getPagina() {
                return pagina;
            }

            @Override
            public Integer getTamanhoPagina() {
                return tamanhoPagina;
            }

            @Override
            public Ordenacao getOrdenacao() {
                return ordenacao;
            }
        };
    }
}
