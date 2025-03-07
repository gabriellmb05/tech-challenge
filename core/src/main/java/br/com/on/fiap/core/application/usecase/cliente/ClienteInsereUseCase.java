package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.ClienteEntrada;

public interface ClienteInsereUseCase {
    Cliente inserir(ClienteEntrada cliente);
}
