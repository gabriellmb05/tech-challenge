package br.com.on.fiap.core.domain.model;

public interface ProdutoFiltro {
    String getNome();

    Categoria getCategoria();

    static ProdutoFiltro create(String nome, Categoria categoria) {
        return new ProdutoFiltro() {

            @Override
            public String getNome() {
                return nome;
            }

            @Override
            public Categoria getCategoria() {
                return categoria;
            }
        };
    }
}
