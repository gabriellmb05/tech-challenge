package br.com.on.fiap.adapter.input.dto.entrada;

import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest implements ProdutoEntrada {

    @NotNull(message = "O atributo nome é obrigatório") private String nome;

    @NotNull(message = "O atributo categoria é obrigatório") private String categoria;

    @NotNull(message = "O atributo preço é obrigatório") private BigDecimal preco;
}
