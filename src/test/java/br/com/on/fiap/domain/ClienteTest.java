package br.com.on.fiap.domain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteTest {

    private Cliente cliente;
    private LocalDate dataNascimento;
    private String cpf;
    private String nome;
    private String email;

    @BeforeEach
    void setUp() {
        this.cpf = "123456789";
        this.nome = "teste1";
        this.email = "teste1@email.com";
        this.dataNascimento = LocalDate.now();
        this.cliente = new Cliente(cpf, nome, dataNascimento, email);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ctor(){
        Cliente value = new Cliente(cpf, nome, dataNascimento, email);
        assertThat(value).isInstanceOf(Cliente.class);
    }
    @Test
    void getCpf() {
        String value = cliente.getCpf();
        assertThat(value).isEqualTo(cpf);
    }

    @Test
    void setCpf() {
        String expected = "987654321";
        cliente.setCpf(expected);
        assertThat(cliente.getCpf()).isEqualTo(expected);
    }

    @Test
    void getNome() {
        String value = cliente.getNome();
        assertThat(value).isEqualTo(nome);
    }

    @Test
    void setNome() {
        String expected = "Teste Alterado";
        cliente.setNome(expected);
        assertThat(cliente.getNome()).isEqualTo(expected);
    }

    @Test
    void getEmail() {
        String value = cliente.getEmail();
        assertThat(value).isEqualTo(email);
    }

    @Test
    void setEmail() {
        String expected = "teste@alterado.com";
        cliente.setEmail(expected);
        assertThat(cliente.getEmail()).isEqualTo(expected);
    }

    @Test
    void getDataNascimento() {
        LocalDate value = cliente.getDataNascimento();
        assertThat(value).isEqualTo(dataNascimento);
    }

    @Test
    void setDataNascimento() {
        LocalDate expected = LocalDate.now();
        cliente.setDataNascimento(expected);
        assertThat(cliente.getDataNascimento()).isEqualTo(expected);
    }
}