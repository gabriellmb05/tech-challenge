package br.com.on.fiap.core.application.dto.entrada.cliente;

import java.time.LocalDate;

public interface ClienteEntrada {
    String getNome();

    String getCpf();

    String getEmail();

    LocalDate getDataNascimento();
}
