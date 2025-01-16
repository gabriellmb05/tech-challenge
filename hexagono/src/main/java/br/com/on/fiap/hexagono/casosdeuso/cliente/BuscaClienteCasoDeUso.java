package br.com.on.fiap.hexagono.casosdeuso.cliente;

import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.cliente.BuscaClientePorCpfPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;

public class BuscaClienteCasoDeUso implements BuscaClientePorCpfPortaEntrada {

	private final PersisteClientePortaSaida persisteClientePortaSaida;

	public BuscaClienteCasoDeUso(PersisteClientePortaSaida persisteClientePortaSaida) {
		this.persisteClientePortaSaida = persisteClientePortaSaida;
	}

	@Override
	public Cliente buscar(String cpf) {
		return persisteClientePortaSaida.buscaClientePorCpf(cpf).orElseThrow(
				() -> new ClienteNaoEncontradoExcecao(MessageError.MSG_ERRO_CLIENTE_NAO_CADASTRADO.getMensagem(), cpf));
	}
}
