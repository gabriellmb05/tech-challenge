package br.com.on.fiap.core.application.dto.filtro;

public interface ProdutoFiltroEntrada {
    String getNome();

    String getCategoria();

    static ProdutoFiltroEntrada create(String nome, String categoria) {
        return new ProdutoFiltroEntrada() {

            @Override
            public String getNome() {
                return nome;
            }

            @Override
            public String getCategoria() {
                return categoria;
            }
        };
    }
}
