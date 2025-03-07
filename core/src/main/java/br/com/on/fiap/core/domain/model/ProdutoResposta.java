package br.com.on.fiap.core.domain.model;

import java.math.BigDecimal;

public interface ProdutoResposta {
    Long getId();

    String getNome();

    String getCategoria();

    BigDecimal getPreco();

    static ProdutoResposta create(Produto produto) {
        return new ProdutoResposta() {
            @Override
            public Long getId() {
                return produto.getId();
            }

            @Override
            public String getNome() {
                return produto.getNome();
            }

            @Override
            public String getCategoria() {
                return produto.getCategoria().name();
            }

            @Override
            public BigDecimal getPreco() {
                return produto.getPreco();
            }
        };
    }
}
