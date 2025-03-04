package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.domain.entity.Cliente;

public interface ClienteBuscaPorCpfUseCase {
    Cliente buscar(String cpf);
}
