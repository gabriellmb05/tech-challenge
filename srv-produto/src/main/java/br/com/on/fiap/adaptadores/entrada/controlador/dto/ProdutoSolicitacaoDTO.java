package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoSolicitacaoDTO(
    @NotNull(message = "O atributo nome é obrigatório") String nome,
    @NotNull(message = "O atributo categoria é obrigatório") String categoria,
    @NotNull(message = "O atributo preço é obrigatório") BigDecimal preco)
    implements ProdutoBaseDTO {
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
}
