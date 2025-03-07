package br.com.on.fiap.core.application.dto.resposta;

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
