package br.com.on.fiap.core.application.dto.resposta;

import br.com.on.fiap.core.domain.Cliente;
import java.time.LocalDate;
import java.util.Objects;

public class ClienteResposta {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;

    public ClienteResposta(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public static ClienteResposta fromDomain(Cliente cliente) {
        return new ClienteResposta(
                cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataNascimento());
    }

    public ClienteResposta() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        var that = (ClienteResposta) obj;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.nome, that.nome)
                && Objects.equals(this.cpf, that.cpf)
                && Objects.equals(this.email, that.email)
                && Objects.equals(this.dataNascimento, that.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, email, dataNascimento);
    }

    @Override
    public String toString() {
        return "ClienteRespostaDTO[" + "id="
                + id + ", " + "nome="
                + nome + ", " + "cpf="
                + cpf + ", " + "email="
                + email + ", " + "dataNascimento="
                + dataNascimento + ']';
    }
}
