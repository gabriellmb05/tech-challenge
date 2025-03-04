package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.output.persistence.entity.ClienteEntity;
import br.com.on.fiap.adapter.output.persistence.mapper.ClienteSaidaMapper;
import br.com.on.fiap.adapter.output.persistence.repository.ClienteRepository;
import java.util.Optional;

import br.com.on.fiap.core.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.core.domain.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteDataSourceImpl implements ClienteDataSource {

    private final ClienteRepository clienteRepository;
    private final ClienteSaidaMapper clienteSaidaMapper;

    public ClienteDataSourceImpl(ClienteRepository clienteRepository, ClienteSaidaMapper clienteSaidaMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteSaidaMapper = clienteSaidaMapper;
    }

    @Override
    public Optional<Cliente> findByNmCpf(String cpf) {
        return clienteRepository.findByNmCpf(cpf).map(clienteSaidaMapper::paraCliente);
    }

    @Override
    public Optional<Cliente> findByNmEmail(String email) {
        return clienteRepository.findByNmEmail(email).map(clienteSaidaMapper::paraCliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id).map(clienteSaidaMapper::paraCliente);
    }

    @Override
    public Cliente persist(Cliente cliente) {
        ClienteEntity clienteEntity = clienteSaidaMapper.paraEntidade(cliente);
        ClienteEntity clienteEntitySalva = clienteRepository.save(clienteEntity);
        return clienteSaidaMapper.paraCliente(clienteEntitySalva);
    }
}
