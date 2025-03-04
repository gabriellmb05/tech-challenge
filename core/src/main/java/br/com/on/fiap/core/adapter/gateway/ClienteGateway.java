package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.domain.model.Cliente;
import java.util.Optional;

public interface ClienteGateway {

    Optional<Cliente> buscaClientePorId(Long id);

    Optional<Cliente> buscaClientePorCpf(String cpf);

    Optional<Cliente> buscaClientePorEmail(String email);

    Cliente salvaCliente(Cliente cliente);
}
