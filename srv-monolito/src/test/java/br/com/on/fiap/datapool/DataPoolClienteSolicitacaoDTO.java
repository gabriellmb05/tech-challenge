package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.ClienteSolicitacaoDTO;
import java.time.LocalDate;
import net.datafaker.Faker;

public class DataPoolClienteSolicitacaoDTO {

    private static final Faker faker = new Faker();

    private static ClienteSolicitacaoDTO construirCliente(
            String nome, String cpf, String email, LocalDate dataNascimento) {
        return ClienteSolicitacaoDTO.builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .dataNascimento(dataNascimento)
                .build();
    }

    public static ClienteSolicitacaoDTO gerarComCpf(String cpf) {
        return construirCliente(faker.name().fullName(), cpf, faker.internet().emailAddress(), LocalDate.now());
    }

    public static ClienteSolicitacaoDTO gerarComNome(String nome) {
        return construirCliente(nome, faker.cpf().valid(), faker.internet().emailAddress(), LocalDate.now());
    }

    public static ClienteSolicitacaoDTO gerarComEmail(String email) {
        return construirCliente(faker.name().fullName(), faker.cpf().valid(), email, LocalDate.now());
    }

    public static ClienteSolicitacaoDTO gerarComCpfEmail(String cpf, String email) {
        return construirCliente(faker.name().fullName(), cpf, email, LocalDate.now());
    }

    public static ClienteSolicitacaoDTO gerarCliente() {
        return construirCliente("Elmo Cameron", "43316652616", "lectus@yahoo.edu", LocalDate.now());
    }
}
