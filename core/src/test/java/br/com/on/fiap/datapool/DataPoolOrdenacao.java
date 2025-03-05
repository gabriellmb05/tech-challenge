package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Direcao;
import br.com.on.fiap.core.domain.model.Ordenacao;

public class DataPoolOrdenacao {

    private DataPoolOrdenacao() {}

    public static Ordenacao criarOrdenacao(String campo, Direcao direcao) {
        return Ordenacao.create(campo, direcao);
    }
}
