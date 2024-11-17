package br.com.on.fiap.repository;

import br.com.on.fiap.domain.Cliente;

import java.util.Collection;

public interface IClienteRepository{
    Cliente findByCpf(String cpf);

    Collection<Cliente> findAllByCpf(String cpf);
}