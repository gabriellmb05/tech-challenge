package br.com.on.fiap.hexagono.portas.saida.cliente;

import br.com.on.fiap.hexagono.dominio.Cliente;
import java.util.Optional;

public interface PersisteClientePortaSaida {

	Optional<Cliente> buscaClientePorCpf(String cpf);

	Optional<Cliente> buscaClientePorCpfOuEmail(String cpf, String email);

	Cliente salvaCliente(Cliente cliente);
}
