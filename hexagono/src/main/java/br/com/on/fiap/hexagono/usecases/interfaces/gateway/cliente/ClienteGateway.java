package br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente;

import br.com.on.fiap.hexagono.entities.entidades.Cliente;
import java.util.Optional;

public interface ClienteGateway {

    Optional<Cliente> buscaClientePorId(Long id);

    Optional<Cliente> buscaClientePorCpf(String cpf);

    Optional<Cliente> buscaClientePorEmail(String email);

    Cliente salvaCliente(Cliente cliente);
}
