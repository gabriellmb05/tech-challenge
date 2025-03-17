package br.com.on.fiap.core.application.dto.entrada;

import java.math.BigDecimal;

public interface ProdutoEntrada {
    String getNome();

    String getCategoria();

    BigDecimal getPreco();

    static ProdutoEntrada create(String nome, String categoria, BigDecimal preco) {
        return new ProdutoEntrada() {
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
