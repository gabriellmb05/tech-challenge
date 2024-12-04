package br.com.on.fiap.dominio.portas.interfaces;

import br.com.on.fiap.dominio.Cliente;

public interface IClienteService {
    Cliente findById(Long Id);

    Cliente findByCpf(String cpf);
}
