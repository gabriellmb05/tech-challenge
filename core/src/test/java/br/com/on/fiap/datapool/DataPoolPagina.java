package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Pagina;
import java.util.List;

public class DataPoolPagina {

    private DataPoolPagina() {}

    public static <T> Pagina<T> criarPaginacao(
            List<T> conteudo, Long totalElementos, Integer totalPaginas, Integer tamanhoPagina, Integer paginaAtual) {
        return Pagina.create(conteudo, totalElementos, totalPaginas, tamanhoPagina, paginaAtual);
    }
}
