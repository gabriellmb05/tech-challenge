package br.com.on.fiap.hexagono.adaptadores.gateways;

import br.com.on.fiap.hexagono.entidades.Cliente;
import java.util.Optional;

public interface ClienteGateway {

    Optional<Cliente> buscaClientePorId(Long id);

    Optional<Cliente> buscaClientePorCpf(String cpf);

    Optional<Cliente> buscaClientePorEmail(String email);

    Cliente salvaCliente(Cliente cliente);
}
