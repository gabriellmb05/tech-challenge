package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteSolicitacaoDTO;
import net.datafaker.Faker;

import java.time.LocalDate;

public class DataPoolClienteSolicitacaoDTO {

	private static final Faker faker = new Faker();

	private static ClienteSolicitacaoDTO construirCliente(String nome, String cpf, String email,
			LocalDate dataNascimento) {
		return ClienteSolicitacaoDTO.builder().nome(nome).cpf(cpf).email(email).dataNascimento(dataNascimento).build();
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
}