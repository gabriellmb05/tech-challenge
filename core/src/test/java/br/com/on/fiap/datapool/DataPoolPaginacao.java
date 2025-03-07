package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.OrdenacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;

public class DataPoolPaginacao {

    private DataPoolPaginacao() {}

    public static PaginacaoResposta criarPaginacao(
            Integer pagina, Integer tamanhoPagina, OrdenacaoResposta ordenacaoResposta) {
        return PaginacaoResposta.create(pagina, tamanhoPagina, ordenacaoResposta);
    }
}
