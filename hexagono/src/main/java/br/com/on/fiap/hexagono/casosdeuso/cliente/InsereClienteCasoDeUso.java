package br.com.on.fiap.hexagono.casosdeuso.cliente;

import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.excecao.ApplicationExcecaoPadrao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.cliente.InsereClientePortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import java.util.Optional;

public class InsereClienteCasoDeUso implements InsereClientePortaEntrada {

	private final PersisteClientePortaSaida persisteClientePortaSaida;

	public InsereClienteCasoDeUso(PersisteClientePortaSaida persisteClientePortaSaida) {
		this.persisteClientePortaSaida = persisteClientePortaSaida;
	}

	@Override
	public Cliente inserir(Cliente cliente) {
		persisteClientePortaSaida.buscaClientePorCpfOuEmail(cliente.getCpf(), cliente.getEmail()).ifPresent(p -> {
			String text = p.getCpf().equals(cliente.getCpf()) ? "CPF" : "E-mail";
			String valor = p.getCpf().equals(cliente.getCpf()) ? cliente.getCpf() : cliente.getEmail();
			throw new ApplicationExcecaoPadrao(MessageError.MSG_ERRO_CPF_OU_EMAIL_JA_CADASTRADO.getMensagem(), text,
					valor);
		});

		return persisteClientePortaSaida.salvaCliente(cliente);
	}

}
