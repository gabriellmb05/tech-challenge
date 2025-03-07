package br.com.on.fiap.core.application.dto.resposta.paginacao;

public enum Direcao {
    ASC("asc"),
    DESC("desc");

    private final String valor;

    Direcao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
