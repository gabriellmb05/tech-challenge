package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Ordenacao;
import br.com.on.fiap.core.domain.model.Paginacao;

public class DataPoolPaginacao {

    private DataPoolPaginacao() {}

    public static Paginacao criarPaginacao(Integer pagina, Integer tamanhoPagina, Ordenacao ordenacao) {
        return Paginacao.create(pagina, tamanhoPagina, ordenacao);
    }
}
