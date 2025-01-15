package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import java.time.LocalDate;

public interface ClienteBaseDTO {

	String getNome();

	String getCpf();

	String getEmail();

	LocalDate getDataNascimento();
}
