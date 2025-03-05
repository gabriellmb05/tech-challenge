package br.com.on.fiap.core.domain.model;

import java.util.List;

public interface Pagina<T> {

    List<T> getConteudo();

    Long getTotalElementos();

    Integer getTotalPaginas();

    Integer getTamanhoPagina();

    Integer getPaginaAtual();
}
