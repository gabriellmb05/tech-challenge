package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClienteRespostaDTO(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
}
