package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record ProdutoDTO (Long id,
                          @NotNull(message = "O atributo nome é obrigatório") String nome,
                          @NotNull(message = "O atributo categoria é obrigatório") CategoriaDTO categoria,
                          @NotNull(message = "O atributo preço é obrigatório") BigDecimal preco){}
