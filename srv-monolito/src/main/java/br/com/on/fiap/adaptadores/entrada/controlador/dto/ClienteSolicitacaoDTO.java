package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Builder
public record ClienteSolicitacaoDTO(@NotNull(message = "O atributo nome é obrigatório") String nome,
		@NotNull(message = "O atributo cpf é obrigatório") @CPF(message = "O CPF informado é inválido") String cpf,
		@NotNull(message = "O atributo email é obrigatório") @Email(message = "O E-mail informado é inválido") String email,
		LocalDate dataNascimento) {
}
