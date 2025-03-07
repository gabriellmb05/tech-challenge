package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.entrada.ClienteRequest;
import java.time.LocalDate;
import net.datafaker.Faker;

public class DataPoolClienteSolicitacaoDTO {

    private static final Faker faker = new Faker();

    private static ClienteRequest construirCliente(String nome, String cpf, String email, LocalDate dataNascimento) {
        return ClienteRequest.builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .dataNascimento(dataNascimento)
                .build();
    }

    public static ClienteRequest gerarComCpf(String cpf) {
        return construirCliente(faker.name().fullName(), cpf, faker.internet().emailAddress(), LocalDate.now());
    }

    public static ClienteRequest gerarComNome(String nome) {
        return construirCliente(nome, faker.cpf().valid(), faker.internet().emailAddress(), LocalDate.now());
    }

    public static ClienteRequest gerarComEmail(String email) {
        return construirCliente(faker.name().fullName(), faker.cpf().valid(), email, LocalDate.now());
    }

    public static ClienteRequest gerarComCpfEmail(String cpf, String email) {
        return construirCliente(faker.name().fullName(), cpf, email, LocalDate.now());
    }

    public static ClienteRequest gerarCliente() {
        return construirCliente("Elmo Cameron", "43316652616", "lectus@yahoo.edu", LocalDate.now());
    }
}
