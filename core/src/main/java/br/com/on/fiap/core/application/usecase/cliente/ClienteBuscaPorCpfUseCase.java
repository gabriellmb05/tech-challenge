package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.domain.model.Cliente;

public interface ClienteBuscaPorCpfUseCase {
    Cliente buscar(String cpf);
}
