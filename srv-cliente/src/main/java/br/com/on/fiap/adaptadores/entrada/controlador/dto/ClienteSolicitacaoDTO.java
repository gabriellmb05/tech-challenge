package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClienteSolicitacaoDTO(@NotNull(message = "O atributo nome é obrigatório") String nome,
		@NotNull(message = "O atributo CPF é obrigatório") @CPF(message = "O CPF informado é inválido") String cpf,
		@NotNull(message = "O atributo E-mail é obrigatório") @Email(message = "O E-mail informado é inválido") String email,
		LocalDate dataNascimento) implements ClienteBaseDTO {

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
