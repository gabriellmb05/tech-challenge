package br.com.on.fiap.core.application.dto.filtro;

import br.com.on.fiap.core.domain.Categoria;

public interface ProdutoFiltroEntrada {
    String getNome();

    Categoria getCategoria();

    static ProdutoFiltroEntrada create(String nome, Categoria categoria) {
        return new ProdutoFiltroEntrada() {

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
