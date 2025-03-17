package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import java.util.List;

public class PaginaRespostaDataPool {

    private PaginaRespostaDataPool() {}

    public static <T> PaginaResposta<T> criarPaginaComPaginacao(
            List<T> conteudo, Long totalElementos, Integer totalPaginas, Integer tamanhoPagina, Integer paginaAtual) {
        return PaginaResposta.create(conteudo, totalElementos, totalPaginas, tamanhoPagina, paginaAtual);
    }
}
