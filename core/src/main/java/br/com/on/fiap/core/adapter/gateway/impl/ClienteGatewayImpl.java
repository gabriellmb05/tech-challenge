package br.com.on.fiap.core.adapter.gateway.impl;

import br.com.on.fiap.core.adapter.datasource.ClienteDataSource;
import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.domain.Cliente;
import java.util.Optional;

public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteDataSource clienteDataSource;

    public ClienteGatewayImpl(ClienteDataSource clienteDataSource) {
        this.clienteDataSource = clienteDataSource;
    }

    @Override
    public Optional<Cliente> buscaClientePorCpf(String cpf) {
        return clienteDataSource.findByNmCpf(cpf);
    }

    @Override
    public Optional<Cliente> buscaClientePorId(Long id) {
        return clienteDataSource.findById(id);
    }

    @Override
    public Optional<Cliente> buscaClientePorEmail(String email) {
        return clienteDataSource.findByNmEmail(email);
    }

    @Override
    public Cliente salvaCliente(Cliente cliente) {
        return clienteDataSource.persist(cliente);
    }
}
