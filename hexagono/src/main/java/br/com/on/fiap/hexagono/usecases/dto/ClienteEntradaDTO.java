package br.com.on.fiap.hexagono.usecases.dto;

import java.time.LocalDate;
import java.util.Objects;

public final class ClienteEntradaDTO {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;

    public ClienteEntradaDTO(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public ClienteEntradaDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ClienteEntradaDTO) obj;
        return Objects.equals(this.nome, that.nome)
                && Objects.equals(this.cpf, that.cpf)
                && Objects.equals(this.email, that.email)
                && Objects.equals(this.dataNascimento, that.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email, dataNascimento);
    }

    @Override
    public String toString() {
        return "ClienteEntradaDTO[" + "nome="
                + nome + ", " + "cpf="
                + cpf + ", " + "email="
                + email + ", " + "dataNascimento="
                + dataNascimento + ']';
    }
}
