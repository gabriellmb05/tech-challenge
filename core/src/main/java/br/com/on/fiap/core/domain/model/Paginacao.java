package br.com.on.fiap.core.domain.model;

public class Paginacao {

    private Integer pagina;
    private Integer tamanhoPagina;
    private Ordenacao ordenacao;

    public Paginacao() {}

    public Paginacao(Integer pagina, Integer tamanhoPagina, Ordenacao ordenacao) {
        this.pagina = pagina;
        this.tamanhoPagina = tamanhoPagina;
        this.ordenacao = ordenacao;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(Integer tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }

    public Ordenacao getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Ordenacao ordenacao) {
        this.ordenacao = ordenacao;
    }
}
