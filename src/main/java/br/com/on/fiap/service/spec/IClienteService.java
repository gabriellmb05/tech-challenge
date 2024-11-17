package br.com.on.fiap.service.spec;

import br.com.on.fiap.domain.Cliente;

public interface IClienteService {
    Cliente findById(Long Id);

    Cliente findByCpf(String cpf);
}
