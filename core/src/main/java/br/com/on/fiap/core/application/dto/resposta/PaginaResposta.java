package br.com.on.fiap.core.application.dto.resposta;

import java.util.List;
import java.util.function.Function;

public interface PaginaResposta<T> {

    List<T> getConteudo();

    Long getTotalElementos();

    Integer getTotalPaginas();

    Integer getTamanhoPagina();

    Integer getPaginaAtual();

    <U> PaginaResposta<U> map(Function<? super T, ? extends U> converter);
}
