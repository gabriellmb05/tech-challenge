package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ClienteSolicitacaoDTO(
    @NotNull(message = "O atributo nome é obrigatório") String nome,
    @NotNull(message = "O atributo cpf é obrigatório") String cpf,
    @NotNull(message = "O atributo email é obrigatório") String email,
    @NotNull(message = "O atributo data nascimento é obrigatório") LocalDate dataNascimento)
    implements ClienteBaseDTO {

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getCpf() {
    return cpf;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }
}
