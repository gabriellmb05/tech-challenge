package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.Direcao;
import br.com.on.fiap.core.application.dto.resposta.OrdenacaoResposta;

public class DataPoolOrdenacao {

    private DataPoolOrdenacao() {}

    public static OrdenacaoResposta criarOrdenacao(String campo, Direcao direcao) {
        return OrdenacaoResposta.create(campo, direcao);
    }
}
