package br.com.on.fiap.dominio.portas.repositorios;

import br.com.on.fiap.dominio.Cliente;
import java.util.List;

public interface IClienteRepository{
    Cliente findByCpf(String cpf);

    List<Cliente> findAll();
}