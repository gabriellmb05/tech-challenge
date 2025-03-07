package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import java.util.List;

public class DataPoolPagina {

    private DataPoolPagina() {}

    public static <T> PaginaResposta<T> criarPaginacao(
            List<T> conteudo, Long totalElementos, Integer totalPaginas, Integer tamanhoPagina, Integer paginaAtual) {
        return PaginaResposta.create(conteudo, totalElementos, totalPaginas, tamanhoPagina, paginaAtual);
    }
}
