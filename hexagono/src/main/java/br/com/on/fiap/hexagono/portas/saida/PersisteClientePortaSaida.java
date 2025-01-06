package br.com.on.fiap.hexagono.portas.saida;

import br.com.on.fiap.hexagono.dominio.Cliente;
import java.util.Optional;

public interface PersisteClientePortaSaida {

	Optional<Cliente> buscaClientePorCpf(String cpf);

	Cliente salvaCliente(Cliente cliente);

	Optional<Cliente> buscaClientePorNome(String nome);

	void deletaClientePorId(Long id);
}
