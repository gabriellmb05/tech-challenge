package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.OrdenacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;

public class PaginacaoRespostaDataPool {

    private PaginacaoRespostaDataPool() {}

    public static PaginacaoResposta criarPaginacaoComOrdenacao(
            Integer pagina, Integer tamanhoPagina, OrdenacaoResposta ordenacaoResposta) {
        return PaginacaoResposta.create(pagina, tamanhoPagina, ordenacaoResposta);
    }
}
