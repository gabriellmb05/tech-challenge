package br.com.on.fiap.core.application.dto.resposta;

public interface OrdenacaoResposta {

    String getCampo();

    Direcao getDirecao();

    static OrdenacaoResposta create(String campo, Direcao direcao) {
        return new OrdenacaoResposta() {
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
