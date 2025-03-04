package br.com.on.fiap.core.application.dto;

import java.math.BigDecimal;

public interface ProdutoEntradaDTO {
    String getNome();

    String getCategoria();

    BigDecimal getPreco();
}
