package br.com.on.fiap.core.domain.model;

public interface Ordenacao {

    String getCampo();

    Direcao getDirecao();

    static Ordenacao create(String campo, Direcao direcao) {
        return new Ordenacao() {
            @Override
            public String getCampo() {
                return campo;
            }

            @Override
            public Direcao getDirecao() {
                return direcao;
            }
        };
    }
}
