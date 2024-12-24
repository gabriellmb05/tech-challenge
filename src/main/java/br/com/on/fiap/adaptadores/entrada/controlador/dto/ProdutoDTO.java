package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    @NotNull(message = "O atributo nome é obrigatório")
    private String nome;

    @NotNull(message = "O atributo categoria é obrigatório")
    private CategoriaDTO categoria;

    @NotNull(message = "O atributo preço é obrigatório")
    private BigDecimal preco;
}
