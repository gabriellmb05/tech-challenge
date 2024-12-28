package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import java.math.BigDecimal;

public interface ProdutoBaseDTO {
  String getNome();

  String getCategoria();

  BigDecimal getPreco();
}
