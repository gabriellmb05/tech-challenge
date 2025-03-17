package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.domain.Cliente;

public interface ClienteBuscaPorIdUseCase {

    Cliente buscar(Long id);
}
