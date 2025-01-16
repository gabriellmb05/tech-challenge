package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoSolicitacaoDTO(@NotNull(message = "O atributo nome é obrigatório") String nome,
		@NotNull(message = "O atributo categoria é obrigatório") String categoria,
		@NotNull(message = "O atributo preço é obrigatório") BigDecimal preco) {
}
