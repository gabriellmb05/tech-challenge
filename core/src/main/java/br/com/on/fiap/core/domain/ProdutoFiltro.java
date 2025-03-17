package br.com.on.fiap.core.domain;

import java.util.Objects;

public class ProdutoFiltro {

    private final String nome;

    private final Categoria categoria;

    public ProdutoFiltro(String nome, String categoria) {
        this.nome = nome;
        this.categoria = getCategoria(categoria);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProdutoFiltro that = (ProdutoFiltro) o;
        return Objects.equals(getNome(), that.getNome()) && Objects.equals(getCategoria(), that.getCategoria());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getNome());
        result = 31 * result + Objects.hashCode(getCategoria());
        return result;
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
