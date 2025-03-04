package br.com.on.fiap.hexagono.adapter.gateway;

import br.com.on.fiap.hexagono.domain.entity.Cliente;
import java.util.Optional;

public interface ClienteGateway {

    Optional<Cliente> buscaClientePorId(Long id);

    Optional<Cliente> buscaClientePorCpf(String cpf);

    Optional<Cliente> buscaClientePorEmail(String email);

    Cliente salvaCliente(Cliente cliente);
}
