package br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProdutoSolicitacaoDTO(
        @NotNull(message = "O atributo nome é obrigatório") String nome,
        @NotNull(message = "O atributo categoria é obrigatório") String categoria,
        @NotNull(message = "O atributo preço é obrigatório") BigDecimal preco,
        String ingredientes) {}
