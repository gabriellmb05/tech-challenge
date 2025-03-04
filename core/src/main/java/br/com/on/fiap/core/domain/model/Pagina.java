package br.com.on.fiap.core.domain.model;

import java.util.List;

public class Pagina<T> {

    private List<T> conteudo;
    private Long totalElementos;
    private Integer totalPaginas;
    private Integer tamanhoPagina;
    private Integer paginaAtual;

    public Pagina() {}

    public Pagina(
            List<T> conteudo, Long totalElementos, Integer totalPaginas, Integer tamanhoPagina, Integer paginaAtual) {
        this.conteudo = conteudo;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
        this.tamanhoPagina = tamanhoPagina;
        this.paginaAtual = paginaAtual;
    }

    public List<T> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<T> conteudo) {
        this.conteudo = conteudo;
    }

    public Long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(Long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Integer getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(Integer tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
}
