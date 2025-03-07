package br.com.on.fiap.core.domain;

public class ProdutoFiltro {

    private final String nome;

    private final Categoria categoria;

    public ProdutoFiltro(String nome, String categoria) {
        this.nome = nome;
        this.categoria = getCategoria(categoria);
    }

    private Categoria getCategoria(String categoria) {
        Categoria categoriaEnum = null;
        if (categoria != null) categoriaEnum = Categoria.deString(categoria);
        return categoriaEnum;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
