package br.com.on.fiap.core.domain.model;

public interface Paginacao {
    Integer getPagina();

    Integer getTamanhoPagina();

    Ordenacao getOrdenacao();
}
