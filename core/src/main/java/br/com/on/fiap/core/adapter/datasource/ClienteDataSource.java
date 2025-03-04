package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.domain.model.Cliente;
import java.util.Optional;

public interface ClienteDataSource {

    Optional<Cliente> findByNmCpf(String cpf);

    Optional<Cliente> findByNmEmail(String email);

    Optional<Cliente> findById(Long id);

    Cliente persist(Cliente cliente);
}
