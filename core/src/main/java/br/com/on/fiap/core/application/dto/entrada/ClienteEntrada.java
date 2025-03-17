package br.com.on.fiap.core.application.dto.entrada;

import java.time.LocalDate;

public interface ClienteEntrada {

    String getNome();

    String getCpf();

    String getEmail();

    LocalDate getDataNascimento();

    static ClienteEntrada create(String nome, String cpf, String email, LocalDate dataNascimento) {
        return new ClienteEntrada() {
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
        };
    }
}
