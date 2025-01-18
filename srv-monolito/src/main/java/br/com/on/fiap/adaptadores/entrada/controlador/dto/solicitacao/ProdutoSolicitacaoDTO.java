package br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoSolicitacaoDTO {

    @NotNull(message = "O atributo nome é obrigatório") private String nome;

    @NotNull(message = "O atributo categoria é obrigatório") private String categoria;

    @NotNull(message = "O atributo preço é obrigatório") private BigDecimal preco;
}
