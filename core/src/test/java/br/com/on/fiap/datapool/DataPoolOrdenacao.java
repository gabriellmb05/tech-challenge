package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.Direcao;
import br.com.on.fiap.core.application.dto.resposta.Ordenacao;

public class DataPoolOrdenacao {

    private DataPoolOrdenacao() {}

    public static Ordenacao criarOrdenacao(String campo, Direcao direcao) {
        return Ordenacao.create(campo, direcao);
    }
}
