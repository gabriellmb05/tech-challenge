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
		Optional<Cliente> clienteBancoDados = persisteClientePortaSaida.buscaClientePorCpf(cliente.getCpf());
		clienteBancoDados.ifPresent(p -> {
			throw new ApplicationExcecaoPadrao(MessageError.MSG_ERRO_CLIENTE_JA_CADASTRADO.getMensagem(),
					cliente.getCpf());
		});
		return persisteClientePortaSaida.salvaCliente(cliente);
	}
}