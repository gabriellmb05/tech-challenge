package br.com.on.fiap.core.domain.model;

import java.time.LocalDate;

public interface ClienteEntrada {
    String getNome();

    String getCpf();

    String getEmail();

    LocalDate getDataNascimento();
}
