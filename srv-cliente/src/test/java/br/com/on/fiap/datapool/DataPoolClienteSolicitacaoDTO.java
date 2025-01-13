package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteSolicitacaoDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import net.datafaker.Faker;

public class DataPoolClienteSolicitacaoDTO {

	private static Faker faker;

	private DataPoolClienteSolicitacaoDTO() {
		faker = new Faker();
	}

	private static ClienteSolicitacaoDTO construirCliente(String nome, String cpf, String email,
			LocalDate dataNascimento) {
		return ClienteSolicitacaoDTO.builder().nome(nome).cpf(cpf).email(email).dataNascimento(dataNascimento).build();
	}

	private static ClienteSolicitacaoDTO criarClienteComDadosFakes() {
		return construirCliente(faker.name().fullName(), faker.cpf().valid(), faker.internet().emailAddress(),
				LocalDate.now());
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

	public static ClienteSolicitacaoDTO gerarClienteComDadosFakes() {
		return criarClienteComDadosFakes();
	}

	public static List<ClienteSolicitacaoDTO> gerarClientesFakes(int quantidade) {
		return IntStream.range(0, quantidade).mapToObj(i -> criarClienteComDadosFakes()).toList();
	}
}