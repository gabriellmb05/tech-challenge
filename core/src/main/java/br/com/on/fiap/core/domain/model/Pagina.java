package br.com.on.fiap.core.domain.model;

import java.util.List;
import java.util.function.Function;

public interface Pagina<T> {

    List<T> getConteudo();

    Long getTotalElementos();

    Integer getTotalPaginas();

    Integer getTamanhoPagina();

    Integer getPaginaAtual();

    <U> Pagina<U> map(Function<? super T, ? extends U> converter);
}
