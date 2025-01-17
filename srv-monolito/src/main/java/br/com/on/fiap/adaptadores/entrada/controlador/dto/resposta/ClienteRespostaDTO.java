package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ClienteRespostaDTO(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {}
