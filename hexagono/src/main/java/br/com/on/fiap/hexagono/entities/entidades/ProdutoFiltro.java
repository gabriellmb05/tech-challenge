package br.com.on.fiap.hexagono.entities.entidades;

public class ProdutoFiltro {

    private String nome;
    private Categoria categoria;

    public ProdutoFiltro() {}

    public ProdutoFiltro(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
