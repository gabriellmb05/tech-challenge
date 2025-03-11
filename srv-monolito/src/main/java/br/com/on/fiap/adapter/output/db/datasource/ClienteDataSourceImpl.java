package br.com.on.fiap.adapter.output.db.datasource;

import br.com.on.fiap.adapter.output.db.entity.ClienteEntity;
import br.com.on.fiap.adapter.output.db.repository.ClienteRepository;
import br.com.on.fiap.core.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.core.domain.Cliente;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClienteDataSourceImpl implements ClienteDataSource {

    private final ClienteRepository clienteRepository;

    public ClienteDataSourceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> findByNmCpf(String cpf) {
        return clienteRepository.findByNmCpf(cpf).map(ClienteEntity::toDomain);
    }

    @Override
    public Optional<Cliente> findByNmEmail(String email) {
        return clienteRepository.findByNmEmail(email).map(ClienteEntity::toDomain);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id).map(ClienteEntity::toDomain);
    }

    @Override
    public Cliente persist(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromDomain(cliente);
        ClienteEntity clienteEntitySalva = clienteRepository.save(clienteEntity);
        return clienteEntitySalva.toDomain();
    }
}
