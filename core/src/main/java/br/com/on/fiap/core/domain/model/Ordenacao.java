package br.com.on.fiap.core.domain.model;

public class Ordenacao {

    private String campo;
    private Direcao direcao;

    public Ordenacao() {}

    public Ordenacao(String campo, Direcao direcao) {
        this.campo = campo;
        this.direcao = direcao;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
