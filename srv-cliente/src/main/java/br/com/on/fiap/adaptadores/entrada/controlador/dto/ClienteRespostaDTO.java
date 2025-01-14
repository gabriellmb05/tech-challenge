package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClienteRespostaDTO(Long id, String nome, String cpf, String email,
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
