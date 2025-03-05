package br.com.on.fiap.core.domain.model;

import java.math.BigDecimal;

public interface ProdutoResposta {
    Long getId();

    String getNome();

    String getCategoria();

    BigDecimal getPreco();

    static ProdutoResposta create(Long id, String nome, String categoria, BigDecimal preco) {
        return new ProdutoResposta() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public String getNome() {
                return nome;
            }

            @Override
            public String getCategoria() {
                return categoria;
            }

            @Override
            public BigDecimal getPreco() {
                return preco;
            }
        };
    }
}
